<script setup lang="ts">
const auth = useAuthStore()
</script>

<template>
  <title>Twoje forum</title>
  <body class="bg-blue-300 min-h-screen">
  <ul class="text-2xl font-semibold sm:flex justify-around py-1rem color-black border-b-1 border-b-cool-800 mx-2rem">
    <NuxtLink to="/">
      <li>Strona główna</li>
    </NuxtLink>
    <NuxtLink to="/auth/login" v-if="!auth.isLoggedIn">
      <li>zaloguj się</li>
    </NuxtLink>
    <NuxtLink to="/auth/register" v-if="!auth.isLoggedIn">
      <li>załóż konto</li>
    </NuxtLink>
    <NuxtLink to="/auth/logout" v-if="auth.isLoggedIn">
      <li>wyloguj się</li>
    </NuxtLink>

  </ul>
  <NuxtPage/>
  </body>
</template>
