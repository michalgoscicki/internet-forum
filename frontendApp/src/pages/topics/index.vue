<script setup lang="ts">
const topicStore = useTopicStore()
onMounted(async () => {
  await topicStore.refreshTopicList()
})
const router = useRouter()
</script>

<template>
    <single-topic v-for="topic in topicStore.topicList" :key="topic.id"
                  :id="topic.id"
                  :title="topic.title"
                  :content="topic.content"
                  :author="topic.author"
                  :posts="topic.posts"
                  @click="router.push(`/topics/${topic.id}`)"
    />
  <div class="flex">
    <topic-creation-form class="mx-auto mt-4rem" />
  </div>
</template>
