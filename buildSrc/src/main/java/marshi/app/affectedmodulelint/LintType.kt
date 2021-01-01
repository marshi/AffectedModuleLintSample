package marshi.app.affectedmodulelint

enum class LintType(val taskName: String, val group: String, val description: String) {
  Lint(
    taskName = "runAffectedLint",
    group = "affected module detector",
    description = "run affected lint"
  ),
  KtLint(
    taskName = "runAffectedKtLint",
    group = "affected module detector",
    description = "run affected ktlint"
  )
}
