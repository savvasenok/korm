package xyz.savvamirzoyan.android.korm.utils

internal fun String.toNumericValue(): Double? {
    val cleanedString = this.replace(" ", "").replace(',', '.')
    val numberRegex = Regex("^-?(\\d+(\\.\\d*)?|\\.\\d+)$")

    if (!cleanedString.matches(numberRegex)) {
        return null // The string contains invalid characters or has an incorrect structure.
    }

    return cleanedString.toDoubleOrNull()
}