# Contributing

Pocket League is open to contributions! This is a project I tinker around with for fun, mostly for my own exploration and interests, but perhaps something that will be released out there to the Rocket League community in the future. 

### Small Ask

If you are planning on contributing to the Pocket League app, please record a [GitHub Issue](https://github.com/pocketleague/issues/new) of what you plan to work on. This is for two main reasons:

1. Awareness! If I know someone of the community is working on a piece of code or feature, I'll try my best not to shake up that same piece of code, and limit confusion when a PR is created.
2. Feedback! Perhaps I've already considered a feature or idea, I'd love to just provide feedback on how something might be implemented, to save contributors time and confusion when a PR is created. 

I will still happily review and discuss any PR that is made, but a notification of incoming changes allows us to have those conversations earlier. 

## Potential Build Failures

For new contributors to the Pocket League app, you may encounter a few common build failures when making changes in the app, if you're not familiar with some of the underlying tools.

### Lint checks

Pocket League uses a few third party tools for static analysis. If your code does not conform to one of the preset requirements for formatting or organization, you'll notice a workflow fail when your pull request is run. To review how to fix and verify these checks, visit the [static analysis documentation](documentation/StaticAnalysis.md).

### Screenshot Tests

A lot of core components in the app are covered by screenshot tests, which mean if the views are changed, the tests will begin to fail (unless we update the "golden" snapshot that they are compared to). For information on how to do that, checkout the [snapshot test documentation](documentation/SnapshotTests.md). 