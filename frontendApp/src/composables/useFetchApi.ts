type useFetchType = typeof useFetch

export const useFetchApi: useFetchType = (path, options = {}) => {
    const auth = useAuthStore()
    const url = useRuntimeConfig().public.NUXT_APP_BASE_URL + path
    return useFetch(url, {
        ...options,
        onResponseError() {
            if (auth.token) {
                auth.logout()
            }
        }
    });
}
