package jetbrains.buildServer.configs.kotlin.v10.triggers

import jetbrains.buildServer.configs.kotlin.v10.*

/**
 * Triggers the build if the previous build failed after a specified time delay
 *
 * **Example.**
 * Trigger a new build if the last build failed.
 * Wait at least for 30 seconds before adding a new build to the queue.
 * Move the newly triggered build to the top of the queue.
 * ```
 * retryBuild {
 *   delaySeconds = 30
 *   moveToTheQueueTop = true
 * }
 * ```
 *
 * **Example.**
 * Trigger a new build if the last build in the branch with prefix 'feature/' failed. Retry only once.
 * ```
 * retryBuild {
 *   attempts = 1
 *   branchFilter = "+:feature/*"
 * }
 * ```
 * <pre style='display:none'>*/</pre>
 *
 *
 * @see retryBuild
 */
open class RetryBuildTrigger : Trigger {
    constructor(init: RetryBuildTrigger.() -> Unit = {}, base: RetryBuildTrigger? = null): super(base = base as Trigger?) {
        type = "retryBuildTrigger"
        init()
    }

    /**
     * Seconds to wait before adding build to queue
     */
    var delaySeconds by intParameter("enqueueTimeout")

    /**
     * Number of attempts to retry the build
     */
    var attempts by intParameter("retryAttempts")

    /**
     * Move triggered build to the queue top
     */
    var moveToTheQueueTop by booleanParameter()

    /**
     * Whether to trigger a new build with the same revisions or not
     */
    var retryWithTheSameRevisions by booleanParameter("reRunBuildWithTheSameRevisions", trueValue = "true", falseValue = "")

    /**
     * [Branch filter](https://www.jetbrains.com/help/teamcity/?Configuring+Schedule+Triggers#ConfiguringScheduleTriggers-BranchFilterbranchFilter) specifies
     * the set of branches where Retry Build Trigger should attempt to retry builds
     */
    var branchFilter by stringParameter()

}


/**
 * Adds Retry Build Trigger
 *
 * **Example.**
 * Trigger a new build if the last build failed.
 * Wait at least for 30 seconds before adding a new build to the queue.
 * Move the newly triggered build to the top of the queue.
 * ```
 * retryBuild {
 *   delaySeconds = 30
 *   moveToTheQueueTop = true
 * }
 * ```
 *
 * **Example.**
 * Trigger a new build if the last build in the branch with prefix 'feature/' failed. Retry only once.
 * ```
 * retryBuild {
 *   attempts = 1
 *   branchFilter = "+:feature/*"
 * }
 * ```
 * <pre style='display:none'>*/</pre>
 *
 *
 * @see RetryBuildTrigger
 */
fun Triggers.retryBuild(base: RetryBuildTrigger? = null, init: RetryBuildTrigger.() -> Unit = {}) {
    trigger(RetryBuildTrigger(init, base))
}
