package jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures

import jetbrains.buildServer.configs.kotlin.v2019_2.*

/**
 * Project feature enabling integration with [Bugzilla](https://www.jetbrains.com/help/teamcity/?Bugzilla) issue tracker
 *
 * **Example.**
 * It is not recommended to store secure values such as passwords directly in the DSL code,
 * see [Managing Tokens](https://www.jetbrains.com/help/teamcity/storing-project-settings-in-version-control.html#Managing+Tokens)
 * section of our documentation.
 * ```
 * bugzilla {
 *   id = "<Connection ID>" // arbitrary ID, must be unique in the project
 *   displayName = "<Connection display name>"
 *   host = "<Bugzilla URL>""
 *   userName = "<Username>"
 *   password = "credentialsJSON:*****"
 *   issueIdPattern = "<Issue id pattern>" // required, for example """#(\d+)"""
 * }
 * ```
 *
 *
 * @see bugzilla
 */
open class BugzillaIssueTracker() : ProjectFeature() {

    init {
        type = "IssueTracker"
        param("type", "bugzilla")
    }

    constructor(init: BugzillaIssueTracker.() -> Unit): this() {
        init()
    }

    /**
     * Issue tracker integration display name
     */
    var displayName by stringParameter("name")

    /**
     * Bugzilla server URL
     */
    var host by stringParameter()

    /**
     * A username for Bugzilla connection
     */
    var userName by stringParameter("username")

    /**
     * A password for Bugzilla connection
     */
    var password by stringParameter("secure:password")

    /**
     * A [java regular expression](http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html) to find the Bugzilla issue ID
     * in a commit message
     */
    var issueIdPattern by stringParameter("pattern")

    override fun validate(consumer: ErrorConsumer) {
        super.validate(consumer)
        if (displayName == null && !hasParam("name")) {
            consumer.consumePropertyError("displayName", "mandatory 'displayName' property is not specified")
        }
        if (host == null && !hasParam("host")) {
            consumer.consumePropertyError("host", "mandatory 'host' property is not specified")
        }
        if (issueIdPattern == null && !hasParam("pattern")) {
            consumer.consumePropertyError("issueIdPattern", "mandatory 'issueIdPattern' property is not specified")
        }
    }
}


/**
 * Enables integration with [Bugzilla](https://www.jetbrains.com/help/teamcity/?Bugzilla) issue tracker
 *
 * **Example.**
 * It is not recommended to store secure values such as passwords directly in the DSL code,
 * see [Managing Tokens](https://www.jetbrains.com/help/teamcity/storing-project-settings-in-version-control.html#Managing+Tokens)
 * section of our documentation.
 * ```
 * bugzilla {
 *   id = "<Connection ID>" // arbitrary ID, must be unique in the project
 *   displayName = "<Connection display name>"
 *   host = "<Bugzilla URL>""
 *   userName = "<Username>"
 *   password = "credentialsJSON:*****"
 *   issueIdPattern = "<Issue id pattern>" // required, for example """#(\d+)"""
 * }
 * ```
 *
 *
 * @see BugzillaIssueTracker
 */
fun ProjectFeatures.bugzilla(init: BugzillaIssueTracker.() -> Unit): BugzillaIssueTracker {
    val result = BugzillaIssueTracker(init)
    feature(result)
    return result
}
