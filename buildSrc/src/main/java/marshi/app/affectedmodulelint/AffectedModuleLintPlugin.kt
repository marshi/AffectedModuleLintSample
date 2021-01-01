package marshi.app.affectedmodulelint

import com.dropbox.affectedmoduledetector.AffectedModuleDetector
import com.dropbox.affectedmoduledetector.AffectedTestConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class AffectedModuleLintPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    registerExtensions(project)
    project.gradle.projectsEvaluated {
      registerAffectedTestTask(LintType.Lint, project)
      registerAffectedTestTask(LintType.KtLint, project)
    }
  }

  private fun registerAffectedTestTask(
    lintType: LintType,
    rootProject: Project
  ) {
    val task = rootProject.tasks.register(lintType.taskName).get()
    task.group = lintType.group
    task.description = lintType.description

    rootProject.subprojects { project ->
      val pluginIds =
        setOf("com.android.application", "com.android.library", "java-library", "kotlin")
      pluginIds.forEach { pluginId ->
        if ((pluginId == "com.android.application" || pluginId == "com.android.library") || lintType == LintType.Lint) {
          withPlugin(lintType, pluginId, task, project)
        }
        if (lintType == LintType.KtLint) {
          withPlugin(lintType, pluginId, task, project)
        }
      }
    }
  }

  private fun withPlugin(
    lintType: LintType,
    pluginId: String,
    task: Task,
    project: Project
  ) {
    project.pluginManager.withPlugin(pluginId) {
      val path = getAffectedPath(lintType, project)
      path?.let {
        task.dependsOn(it)
      }
    }
  }

  private fun getAffectedPath(
    lintType: LintType,
    project: Project
  ): String? {
    val conf = requireNotNull(
      project.extensions.findByName(AffectedModuleLintConfiguration.name)
    ) {
      "Unable to find ${AffectedTestConfiguration.name} in $project"
    } as AffectedModuleLintConfiguration

    val pathName = when (lintType) {
      LintType.Lint -> getPathAndTask(project, conf.lint)
      LintType.KtLint -> getPathAndTask(project, conf.ktlint)
    }
    return if (AffectedModuleDetector.isProjectAffected(project)) {
      pathName
    } else {
      null
    }
  }

  private fun getPathAndTask(project: Project, task: String?): String? {
    return if (task.isNullOrEmpty()) {
      null
    } else {
      "${project.path}:${task}"
    }
  }

  private fun registerExtensions(project: Project) {
    project.subprojects { subProject ->
      subProject.extensions.add(
        AffectedModuleLintConfiguration.name,
        AffectedModuleLintConfiguration()
      )
    }
  }
}
