<script setup lang="ts">
import type {NewPost} from "~/types";

const props = defineProps<{ topicId: number, refresh: Function }>()

const post = reactive({
  id: props.topicId,
  content: '',
})
const topicStore = useTopicStore()
const createPost = async (post: NewPost) => {
  await topicStore.createPost(post)
  await props.refresh()
  post.content = ''
}
</script>

<template>
  <div class="bg-blue-600 px-2rem py-1rem rounded-2xl mt-1rem shadow-md">
    <p class="text-white font-bold text-xl">Dodaj komentarz</p>
    <form @submit.prevent="createPost(post)" method="POST" name="createTopic">
      <textarea v-model="post.content" name="content" placeholder="Treść" required
                class="w-full p-1rem rounded-lg my-1rem shadow-md"></textarea>
      <button type="submit" class="bg-emerald-500 text-white p-1rem rounded-lg my-1rem shadow-md hover:animate-pulse">
        Skomentuj
      </button>
    </form>
  </div>
</template>
