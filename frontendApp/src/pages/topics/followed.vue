<script setup lang="ts">
const topicStore = useTopicStore()
onMounted(async () => {
  await topicStore.refreshTopicList()
})
const router = useRouter()
</script>

<template>
  <single-topic v-for="topic in topicStore.followedTopicList" :key="topic.id"
                :id="topic.id"
                :title="topic.title"
                :content="topic.content"
                :author="topic.author"
                :posts="topic.posts"
                :refresh="topicStore.refreshFollowedTopicList"
                @click="router.push(`/topics/${topic.id}`)"
  />
  <div class="flex">
    <p v-if="!topicStore.followedTopicList?.length" class="color-black text-xl flex mx-auto mt-1rem">
      Nic nie obserwujesz?
      <NuxtLink to="/topics" class="color-emerald-600 mx-1 font-bold hover:animate-pulse">
        Zacznij zapisywać
      </NuxtLink>
      swoje ulubione wpisy!
    </p>
  </div>
</template>
