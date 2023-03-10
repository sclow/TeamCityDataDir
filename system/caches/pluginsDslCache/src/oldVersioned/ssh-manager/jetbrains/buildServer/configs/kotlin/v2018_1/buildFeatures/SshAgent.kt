package jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures

import jetbrains.buildServer.configs.kotlin.v2018_1.*

/**
 * A [build feature](https://www.jetbrains.com/help/teamcity/?SSH+Agent) which runs
 * SSH agent during a build with the specified SSH key loaded
 *
 * **Example.**
 * Adds an [SSH Agent](https://www.jetbrains.com/help/teamcity/?SSH+Agent) build feature
 * with specified uploaded ssh key ID and passphrase (optional parameter).
 * ```
 * sshAgent {
 *     teamcitySshKey = "id"
 *     passphrase = "credentialsJSON:******"
 * }
 * ```
 *
 *
 * @see sshAgent
 */
open class SshAgent() : BuildFeature() {

    init {
        type = "ssh-agent-build-feature"
    }

    constructor(init: SshAgent.() -> Unit): this() {
        init()
    }

    /**
     * [Uploaded SSH key](https://www.jetbrains.com/help/teamcity/?SSH+Keys+Management) name to load into an SSH agent
     */
    var teamcitySshKey by stringParameter()

    /**
     * SSH key passphrase
     */
    var passphrase by stringParameter("secure:passphrase")

    override fun validate(consumer: ErrorConsumer) {
        super.validate(consumer)
        if (teamcitySshKey == null && !hasParam("teamcitySshKey")) {
            consumer.consumePropertyError("teamcitySshKey", "mandatory 'teamcitySshKey' property is not specified")
        }
    }
}


/**
 * Adds a [build feature](https://www.jetbrains.com/help/teamcity/?SSH+Agent) which runs
 * SSH agent during a build with the specified SSH key loaded
 *
 * **Example.**
 * Adds an [SSH Agent](https://www.jetbrains.com/help/teamcity/?SSH+Agent) build feature
 * with specified uploaded ssh key ID and passphrase (optional parameter).
 * ```
 * sshAgent {
 *     teamcitySshKey = "id"
 *     passphrase = "credentialsJSON:******"
 * }
 * ```
 *
 *
 * @see SshAgent
 */
fun BuildFeatures.sshAgent(init: SshAgent.() -> Unit): SshAgent {
    val result = SshAgent(init)
    feature(result)
    return result
}
