<script setup lang="ts">
const auth = useAuthStore()
if (auth.isLoggedIn) {
  auth.logout()
}
const router = useRouter()
router.push('/auth/login')

</script>

<template>

</template>
