<script setup lang="ts">
const props = defineProps<{ title: string, content: string, refresh: Function, rejectChanges: Function }>()
const topic = reactive({
  title: props.title,
  content: props.content
})
const route = useRoute()
const topicId = parseInt(<string>route.params.id)

const editTopic = async () => {
  const topicStore = useTopicStore()
  await topicStore.editTopic(topicId, topic.title, topic.content)
  await props.refresh()
  props.rejectChanges()
}
</script>

<template>
  <form @submit.prevent="editTopic" method="POST" name="createTopic">
    <input v-model="topic.title" type="text" name="title" placeholder="Tytuł" required
           class="w-full p-1rem rounded-lg my-1rem shadow-md">
    <textarea v-model="topic.content" name="content" placeholder="Treść" required
              class="w-full p-1rem rounded-lg my-1rem shadow-md"></textarea>
    <button type="submit" class="bg-emerald-500 text-white p-1rem rounded-lg my-1rem shadow-md hover:animate-pulse">
      Edytuj
    </button>
    <button @click="props.rejectChanges" type="button"
            class="bg-rose-500 text-white p-1rem rounded-lg ml-1rem my-1rem shadow-md hover:animate-pulse">
      odrzuć
    </button>
  </form>
</template>
