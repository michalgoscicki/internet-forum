// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    devtools: {enabled: true},
    modules: [
        '@pinia/nuxt', '@unocss/nuxt', '@vueuse/nuxt', '@nuxt/ui',
    ],
    ssr: false,
})
