<script setup lang="ts">
const user = reactive({
  username: '',
  password: ''
})

const loginError = ref('')

const login = async () => {
  if (!user.username || !user.password) {
    console.error('Wszystkie pola są wymagane')
    return
  }

  const auth = useAuthStore()
  const router = useRouter()

  await auth.login(user.username, user.password)
      .catch(
          async err => {
            console.error(err.data)
            loginError.value = 'Nie udana próba logowania! Spróbuj ponownie!'
            const errorTime = (ms: number) => new Promise(resolve => setTimeout(resolve, ms));
            await errorTime(2000)
            loginError.value = ''
          }
      )
  if (auth.token) {
    await router.push('/')
  }
}
</script>

<template>
  <div class="lg:w-1/3 md:w-2/3 md:mx-auto mt-1rem">
    <form method="POST" @submit.prevent="login">
      <div class="sm:mx-1rem">
        <label for="username" class="block text-md font-medium leading-6 text-black">Nazwa użytkownika</label>
        <div class="mt-2">
          <input
              id="username"
              v-model="user.username"
              name="username"
              type="text"
              autocomplete="username"
              required
              class="block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-blue-500 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 bg-white p-0.5rem"
          />
        </div>
      </div>

      <div class="sm:mx-1rem mt-1rem relative">
        <label for="password" class="block text-md font-medium leading-6 text-black">Hasło</label>
        <div class="mt-2">
          <input
              id="password"
              v-model="user.password"
              name="password"
              type="password"
              autocomplete="password"
              required
              class="block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-blue-500 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 bg-white p-0.5rem"
          />
        </div>
        <p v-if="loginError" class="text-rose-500 animate-rubber-band font-semibold absolute">{{ loginError }}</p>
        <button class="bg-emerald-600 color-black px-6 py-3 rounded-md mt-2rem" type="submit">Zaloguj się</button>
      </div>


    </form>
  </div>
</template>
