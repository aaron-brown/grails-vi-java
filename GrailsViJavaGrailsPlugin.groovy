/*
 * Copyright 2014 Aaron Brown
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
class GrailsViJavaGrailsPlugin {
    // the plugin version
    def version = "0.0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "docs/",
        "src/docs/",
    ]

    // TODO Fill in these fields
    def title = "Grails VI Java Plugin" // Headline display name of the plugin
    def author = "Aaron Brown"
    def authorEmail = "brown.aaron.lloyd@gmail.com"
    def description = '''\
A "Groovy" wrapper / interface around VMWare VI Java SDK.
'''

    def documentation = "http://aaron-brown.github.io/grails-vi-java/docs/manual/latest/index.html"
    def license = "APACHE"
    def issueManagement = [ system: "GITHUB", url: "https://github.com/aaron-brown/grails-vi-java/issues" ]
    def scm = [ url: "https://github.com/aaron-brown/grails-vi-java" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
