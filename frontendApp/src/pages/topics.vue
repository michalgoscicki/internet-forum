<script setup lang="ts">
onMounted(async () => {
  const auth = useAuthStore()
  if (!auth.isLoggedIn) {
    const router = useRouter()
    await router.push('/auth/login')
  }
})
</script>

<template>
<NuxtPage />
</template>
