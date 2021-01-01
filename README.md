# AffectedModuleLintSample

Sample project for making runAffectedLint task using [dropbox/AffectedModuleDetector](https://github.com/dropbox/AffectedModuleDetector).

dropbox/AffectedModuleDetector have API which detect affected modules.

The API can be also used to execute lint task to only affected modules.

## how to run

```
./gradlew runAffectedLint -Paffected_module_detector.enable -Paffected_module_detector.changedProjects
```

These options are introduced in dropbox/AffectedModuleDetector.

## how to make the task

see this PR.

https://github.com/marshi/AffectedModuleLintSample/pull/1
