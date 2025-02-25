package com.example.testingproject.util

import androidx.drawerlayout.widget.DrawerLayout

object NavigationDrawerLockUtility {

    fun lockNavigationDrawer(drawerLayout: DrawerLayout) {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    fun unlockNavigationDrawer(drawerLayout: DrawerLayout) {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
}