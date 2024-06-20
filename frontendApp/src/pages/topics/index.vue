<script setup lang="ts">
const topicStore = useTopicStore()
onMounted(async () => {
  await topicStore.refreshTopicList()
})
</script>

<template>
  <single-topic v-for="topic in topicStore.topicList" :key="topic.id"
                :id="topic.id"
                :title="topic.title"
                :content="topic.content"
                :author="topic.author"
                :posts="topic.posts"
  />
</template>
