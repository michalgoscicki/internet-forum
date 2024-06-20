<script setup lang="ts">
const props = defineProps<{ postId: number, content: string, refresh: Function, rejectChanges: Function }>()
const post = reactive({
  id: props.postId,
  content: props.content
})
const route = useRoute()
const topicId = parseInt(<string>route.params.id)

const editPost = async () => {
  const topicStore = useTopicStore()
  await topicStore.editPost(topicId, post.id, post.content)
  await props.refresh()
  props.rejectChanges()
}
</script>

<template>
  <form @submit.prevent="editPost" method="POST" name="createTopic">
    <textarea v-model="post.content" name="content" placeholder="Treść" required
              class="w-full p-1rem rounded-lg my-1rem shadow-md"></textarea>
    <button type="submit" class="bg-emerald-500 text-white p-1rem rounded-lg my-1rem shadow-md hover:animate-pulse">
      Edytuj
    </button>
    <button @click="props.rejectChanges" type="button" class="bg-rose-500 text-white p-1rem rounded-lg ml-1rem my-1rem shadow-md hover:animate-pulse">
      odrzuć
    </button>
  </form>
</template>
