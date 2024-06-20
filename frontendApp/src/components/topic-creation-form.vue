<script setup lang="ts">
const topic = reactive({
  title: '',
  content: '',
})
const topicStore = useTopicStore()
const createTopic = async () => {
  await topicStore.createTopic(topic)

  const route = useRoute()
  if (route.path === '/topics') {
    await topicStore.refreshTopicList()
  } else {
    const router = useRouter()
    await router.push('/topics')
  }
}
</script>

<template>
  <div class="bg-blue-600 px-2rem py-1rem rounded-2xl mt-1rem shadow-md">
    <p class="text-white font-bold text-xl">Załóż nowy temat</p>
    <form @submit.prevent="createTopic" method="POST" name="createTopic">
      <input v-model="topic.title" type="text" name="title" placeholder="Tytuł" required
             class="w-full p-1rem rounded-lg my-1rem shadow-md">
      <textarea v-model="topic.content" name="content" placeholder="Treść" required
                class="w-full p-1rem rounded-lg my-1rem shadow-md"></textarea>
      <button type="submit" class="bg-emerald-500 text-white p-1rem rounded-lg my-1rem shadow-md hover:animate-pulse">
        Załóż temat
      </button>
    </form>
  </div>
</template>
