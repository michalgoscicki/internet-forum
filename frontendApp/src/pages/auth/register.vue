<script setup lang="ts">
const emailError = ref('')
const nameError = ref('')
const surnameError = ref('')
const passwordError = ref('')
const repeatedPasswordError = ref('')
const usernameError = ref('')

function validateEmail(email: string): boolean {
  const re = /\S+@\S+\.\S+/
  return re.test(email)
}

function validatePassword(password: string): boolean {
  return password.length >= 8
}

function validateUsername(username: string): boolean {
  return username.length >= 3
}

function validateName(name: string): boolean {
  return name.length >= 3
}

function validateSurname(surname: string): boolean {
  return surname.length >= 3
}

const repeatedPassword = ref('')

const user = reactive({
  id: 0,
  username: '',
  name: '',
  surname: '',
  email: '',
  password: '',
})

watchEffect(() => {
  if (!validateEmail(user.email) && user.email.length > 0) {
    emailError.value = 'Niepoprawny email'
  } else {
    emailError.value = ''
  }
})

watchEffect(() => {
  if (!validatePassword(user.password) && user.password.length > 0) {
    passwordError.value = 'Hasło musi mieć co najmniej 8 znaków'
  } else {
    passwordError.value = ''
  }
})

watchEffect(() => {
  if (user.password !== repeatedPassword.value && repeatedPassword.value.length > 0) {
    repeatedPasswordError.value = 'Hasła nie są takie same'
  } else {
    repeatedPasswordError.value = ''
  }
})

watchEffect(() => {
  if (!validateUsername(user.username) && user.username.length > 0) {
    usernameError.value = 'Nazwa użytkownika musi mieć co najmniej 3 znaki'
  } else {
    usernameError.value = ''
  }
})

watchEffect(() => {
  if (!validateName(user.name) && user.name.length > 0) {
    nameError.value = 'Imię musi się składać z co najmniej 3 znaków'
  } else {
    nameError.value = ''
  }
})

watchEffect(() => {
  if (!validateSurname(user.surname) && user.surname.length > 0) {
    surnameError.value = 'Nazwisko musi się składać z najmniej 3 znaków'
  } else {
    surnameError.value = ''
  }
})

const register = async () => {
  if (!validateEmail(user.email)) {
    return
  }
  if (!validatePassword(user.password)) {
    return
  }
  if (!validateUsername(user.username)) {
    return
  }
  if (repeatedPassword.value !== user.password) {
    return
  }

  const auth = useAuthStore()
  const router = useRouter()

  await auth.register(user)
  await router.push('/auth/login')
}
</script>

<template>
  <div class="lg:w-1/3 md:w-2/3 md:mx-auto mt-1rem">
    <form method="POST" @submit.prevent="register">

      <div class="sm:mx-1rem mt-2rem relative">
        <label for="name" class="block text-md font-medium leading-6 text-black">Imię</label>
        <div class="mt-2">
          <input
              id="name"
              v-model="user.name"
              name="name"
              type="text"
              autocomplete="name"
              required
              class="block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-blue-500 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 bg-white p-0.5rem"
          />
        </div>
        <p class="text-rose-500 font-semibold absolute">{{ nameError }}</p>
      </div>

      <div class="sm:mx-1rem mt-2rem relative">
        <label for="surname" class="block text-md font-medium leading-6 text-black">Nazwisko</label>
        <div class="mt-2">
          <input
              id="surname"
              v-model="user.surname"
              name="surname"
              type="text"
              autocomplete="surname"
              required
              class="block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-blue-500 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 bg-white p-0.5rem"
          />
        </div>
        <p class="text-rose-500 font-semibold absolute">{{ surnameError }}</p>
      </div>

      <div class="sm:mx-1rem mt-2rem relative">
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
        <p class="text-rose-500 font-semibold absolute">{{ usernameError }}</p>
      </div>

      <div class="sm:mx-1rem mt-2rem relative">
        <label for="email" class="block text-md font-medium leading-6 text-black">Email użytkownika</label>
        <div class="mt-2">
          <input
              id="email"
              v-model="user.email"
              name="email"
              type="text"
              autocomplete="email"
              required
              class="block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-blue-500 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 bg-white p-0.5rem"
          />
        </div>
        <p class="text-rose-500 font-semibold absolute">{{ emailError }}</p>
      </div>

      <div class="sm:mx-1rem mt-2rem relative">
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
        <p class="text-rose-500 font-semibold absolute">{{ passwordError }}</p>
      </div>


      <div class="sm:mx-1rem mt-2rem relative">
        <label for="repeatedPassword" class="block text-md font-medium leading-6 text-black">Powtórz hasło</label>
        <div class="mt-2">
          <input
              id="repeatedPassword"
              v-model="repeatedPassword"
              name="repeatedPassword"
              type="password"
              autocomplete="repeatedPassword"
              required
              class="block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-blue-500 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 bg-white p-0.5rem"
          />
        </div>
        <p class="text-rose-500 font-semibold absolute">{{ repeatedPasswordError }}</p>

        <button class="bg-emerald-600 color-black px-6 py-3 rounded-md mt-2rem" type="submit">Załóż konto</button>
      </div>


    </form>
  </div>
</template>
