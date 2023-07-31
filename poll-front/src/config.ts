//https://github.com/vuejs/rfcs/pull/117 i should use this and not any app config method...
export const config = {
    siteUrl: import.meta.env.VITE_SITE_URL,
    apiUrl: import.meta.env.VITE_API_URL
}