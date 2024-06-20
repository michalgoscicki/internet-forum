<script setup lang="ts">
const auth = useAuthStore()
</script>

<template>
  <div class="flex mt-1rem">
    <div class="mx-auto">
      <p v-if="auth.isLoggedIn" class="color-black text-xl flex">Witaj ponownie
        <div class="font-bold italic mx-1">{{ auth.user.username }}</div>
        !
      </p>
      <p v-else class="color-black text-xl flex">
        <NuxtLink to="/auth/login" class="color-emerald-600 mr-1 font-bold hover:animate-pulse">
          Zaloguj się
        </NuxtLink>
        by w pełni korzystać z forum lub
        <NuxtLink to="/auth/register" class="color-emerald-600 mx-1 font-bold hover:animate-pulse">
          załóż konto
        </NuxtLink>
        jeżeli go jeszcze nie masz
      </p>

      <topic-creation-form/>
      <p class="mt-2rem color-black text-2xl flex">lub znajdź coś dla siebie
        <NuxtLink to="/topics">
          <div class="color-emerald-500 hover:color-emerald-600 mx-1 font-bold">klikając tutaj</div>
        </NuxtLink>
      </p>
    </div>
  </div>
</template>
