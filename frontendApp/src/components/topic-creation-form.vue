<script setup lang="ts">
import type {NewTopic} from "~/types";

const topic = reactive({
  title: '',
  content: '',
})
const topicStore = useTopicStore()
const createTopic = (topic: NewTopic) => {
  topicStore.createTopic(topic)
}
</script>

<template>
  <div class="bg-blue-400 px-2rem py-1rem rounded-2xl mt-1rem shadow-md">
    <p class="text-white font-bold text-xl">Załóż nowy temat</p>
    <form @submit.prevent="createTopic(topic)" method="POST" name="createTopic">
      <input v-model="topic.title" type="text" name="title" placeholder="Tytuł" class="w-full p-1rem rounded-lg my-1rem shadow-md">
      <textarea v-model="topic.content" name="content" placeholder="Treść" class="w-full p-1rem rounded-lg my-1rem shadow-md"></textarea>
      <button type="submit" class="bg-emerald-500 text-white p-1rem rounded-lg my-1rem shadow-md hover:animate-pulse">Załóż temat</button>
    </form>
  </div>
</template>
