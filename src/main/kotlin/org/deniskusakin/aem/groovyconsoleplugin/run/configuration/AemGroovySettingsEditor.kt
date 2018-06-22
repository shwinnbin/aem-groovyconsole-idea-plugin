package org.deniskusakin.aem.groovyconsoleplugin.run.configuration

import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextFieldWithBrowseButton

import javax.swing.*

class AemGroovySettingsEditor(private val project: Project) : SettingsEditor<AemGroovyRunConfiguration>() {
    private var contentPane: JPanel? = null
    private lateinit var  serverHost: JTextField
    private lateinit var loginField: JTextField
    private lateinit var passwordField: JPasswordField
    private lateinit var scriptPath: TextFieldWithBrowseButton

    override fun resetEditorFrom(s: AemGroovyRunConfiguration) {
        serverHost.text = s.serverHost
        loginField.text = s.login
        passwordField.text = s.password
        scriptPath.text = s.scriptPath ?: ""
    }

    @Throws(ConfigurationException::class)
    override fun applyEditorTo(runConfiguration: AemGroovyRunConfiguration) {
        runConfiguration.serverHost = serverHost.text
        runConfiguration.login = loginField.text
        runConfiguration.password = passwordField.text
        runConfiguration.scriptPath = scriptPath.text
    }

    override fun createEditor(): JComponent {

        val fileChooserDescriptor = FileChooserDescriptor(true, false, false, false, false, false)
        scriptPath.addBrowseFolderListener("title", "desc", project, fileChooserDescriptor)
        return contentPane!!
    }

    private fun createUIComponents() {
        // TODO: place custom component creation code here
    }
}