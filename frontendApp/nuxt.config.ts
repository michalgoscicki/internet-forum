// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    devtools: {enabled: true},
    modules: [
        '@pinia/nuxt', '@unocss/nuxt', '@vueuse/nuxt', '@nuxt/ui',
    ],
    srcDir: "src/",
    ssr: false,

    pinia: {
        autoImports: [
            'defineStore',
            'acceptHMRUpdate',
        ]
    },

    runtimeConfig: {
        public: {
            NUXT_APP_BASE_URL: process.env.BASEURL
        }
    },

})
