package io.vaiyo.presentation.base.adapter

interface ListItem {
    val listID: String?
    override fun equals(other: Any?): Boolean
}