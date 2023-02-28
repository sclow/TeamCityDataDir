package jetbrains.buildServer.configs.kotlin.buildFeatures

import jetbrains.buildServer.configs.kotlin.*

/**
 * Build feature enabling [automatic VCS labeling](https://www.jetbrains.com/help/teamcity/?VCS+Labeling#VCSLabeling-AutomaticVCSlabeling) in a build.
 *
 * **Example.**
 * Upon finishing of a successful build, add a tag with name 'teamcity-build-%system.build.number%' to the VCS repository of the VCS Root with specified id.
 * Note: parameter reference %system.build.number% will be replaced with the build number of the build.
 * See also [predefined parameters](https://www.jetbrains.com/help/teamcity/predefined-build-parameters.html).
 * ```
 * vcsLabeling {
 *   vcsRootId = "< id of a VCS root>"
 *   labelingPattern = "teamcity-build-%system.build.number%"
 *   successfulOnly = true
 * }
 * ```
 *
 *
 * @see vcsLabeling
 */
open class VcsLabeling() : BuildFeature() {

    init {
        type = "VcsLabeling"
    }

    constructor(init: VcsLabeling.() -> Unit): this() {
        init()
    }

    /**
     * Id of the VCS root which sources should be labeled. Use the constant "__ALL__" to label all VCS roots.
     */
    @Deprecated("Please use vcsRootId instead.", replaceWith = ReplaceWith("vcsRootId"))
    var vcsRootExtId by stringParameter("vcsRootId")

    /**
     * Id of the VCS root which sources should be labeled. Use the constant "__ALL__" to label all VCS roots.
     */
    var vcsRootId by stringParameter()

    /**
     * A pattern for labels to use. If not specified, then the default pattern is used: build-%system.build.number%.
     */
    var labelingPattern by stringParameter()

    /**
     * Whether only successful builds sources should be labeled
     */
    var successfulOnly by booleanParameter(trueValue = "true", falseValue = "")

    /**
     * [Branch filter](https://www.jetbrains.com/help/teamcity/?Configuring+VCS+Triggers#ConfiguringVCSTriggers-BranchFilter) specifies
     * in which branches sources should be labeled
     */
    var branchFilter by stringParameter()

    override fun validate(consumer: ErrorConsumer) {
        super.validate(consumer)
        if (vcsRootExtId == null && !hasParam("vcsRootId")) {
            consumer.consumePropertyError("vcsRootExtId", "mandatory 'vcsRootExtId' property is not specified")
        }
        if (vcsRootId == null && !hasParam("vcsRootId")) {
            consumer.consumePropertyError("vcsRootId", "mandatory 'vcsRootId' property is not specified")
        }
    }
}


/**
 * Enables [automatic VCS labeling](https://www.jetbrains.com/help/teamcity/?VCS+Labeling#VCSLabeling-AutomaticVCSlabeling) in a build
 *
 * **Example.**
 * Upon finishing of a successful build, add a tag with name 'teamcity-build-%system.build.number%' to the VCS repository of the VCS Root with specified id.
 * Note: parameter reference %system.build.number% will be replaced with the build number of the build.
 * See also [predefined parameters](https://www.jetbrains.com/help/teamcity/predefined-build-parameters.html).
 * ```
 * vcsLabeling {
 *   vcsRootId = "< id of a VCS root>"
 *   labelingPattern = "teamcity-build-%system.build.number%"
 *   successfulOnly = true
 * }
 * ```
 *
 *
 * @see VcsLabeling
 */
fun BuildFeatures.vcsLabeling(init: VcsLabeling.() -> Unit): VcsLabeling {
    val result = VcsLabeling(init)
    feature(result)
    return result
}
