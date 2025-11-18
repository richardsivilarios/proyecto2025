import {defineStore} from "pinia";

export const useStore = defineStore('ems', {
    persist: {
        paths: [
            'token',
            'refreshToken',
            'userInfo'
        ]
    },

    state: () => ({
        token: null,
        refreshToken: null,
        userInfo: null,
        isLoadMenu: false,
        routers: null,
        permission: null,
        activeIndex: 'delantera',
        openTabs: []
    }),

    actions: {

        // -------------------------
        // Acciones existentes
        // -------------------------
        loadMenuAction(payload) {
            this.isLoadMenu = payload
        },

        routerAction(payload) {
            this.routers = payload
        },

        permissionAction(payload) {
            this.permission = payload
        },

        tokenAction(payload) {
            this.token = payload
        },

        activeIndexAction(payload) {
            this.activeIndex = payload
        },

        addTabAction(payload) {
            if (this.openTabs.filter(tab => tab.name === payload.name).length === 0) {
                payload.isClose = payload.name !== 'delantera'
                this.openTabs.push(payload)
            }
        },

        removeTabAction(payload) {
            this.openTabs = this.openTabs.filter((tab) => tab.name !== payload)
        },

        clearTabAction() {
            this.openTabs = []
        },

        // -------------------------
        // 🔥 FIX NUEVO
        // -------------------------

        // Forzar recarga de menús
        clearMenuCache() {
            this.routers = null
            this.permission = null
            this.isLoadMenu = false
        },

        // Limpiar sesión completa
        clearAllSession() {
            this.token = null
            this.refreshToken = null
            this.userInfo = null

            this.clearMenuCache()

            this.activeIndex = 'delantera'
            this.openTabs = []
        }
    }
})
