package xyz.savvamirzoyan.android.korm

import android.util.Patterns
import xyz.savvamirzoyan.android.korm.contract.ErrorBuilder
import xyz.savvamirzoyan.android.korm.model.KormFieldId

class DemoErrorBuilder : ErrorBuilder {

    override fun getErrorString(id: KormFieldId, value: String): String? = when (id.value) {
        "user/name" -> userName(value)
        "user/mail" -> userMail(value)
        else -> null
    }

    override fun getErrorString(id: KormFieldId, value: Double?): String? = when (id.value) {
        "user/age" -> userAge(value)
        "user/height" -> userHeight(value)
        else -> null
    }

    override fun getErrorString(id: KormFieldId, value: Int): String? = null

    private fun userName(value: String): String? =
        if (value.isBlank()) "Name must not be blank"
        else if (value == "Blank") "Really?..."
        else null

    private fun userAge(value: Double?): String? =
        if (value == null) "Invalid number"
        else if (value != value.toInt().toDouble()) "Age can not be decimal"
        else if (value < 0) "Can not be negative number"
        else if (value < 18) "Users under 18 are not allowed"
        else if (value > 50) "Users over 50 are not allowed"
        else null

    private fun userHeight(value: Double?): String? =
        if (value == null) "Invalid height"
        else if (value > 2.5) "Too much, man..."
        else null

    private fun userMail(value: String): String? =
        if (value.isBlank()) "Email can not be blank"
        else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) "Invalid email"
        else null
}