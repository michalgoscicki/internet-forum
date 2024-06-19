import type {NewTopic, Topic} from "~/types";

export const useTopicStore = defineStore("topicStore", () => {
    const auth = useAuthStore()

    const {data: topicList, refresh: refreshTopicList} = useFetchApi<Topic[]>("/api/v1/topics", {
        headers: {Authorization: 'Bearer' + auth.token},

    })

    const createTopic = async (topic: NewTopic) => {
        console.log(topic)
        await $fetchApi("/api/v1/topics", {
            method: "POST",
            body: {
                title: topic.title,
                content: topic.content,
            },
            headers: {Authorization: 'Bearer' + auth.token},
        }).catch((error) => console.error(error));
    };

    const deleteTopic = async (topicId: number) => {
        await $fetchApi(`/api/v1/topics/${topicId}`, {
            method: "DELETE",
            headers: {Authorization: 'Bearer' + auth.token},
        }).catch((error) => console.error(error));
    }

    return {
        createTopic,
        deleteTopic,
        topicList,
        refreshTopicList,
    };
});

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useTopicStore, import.meta.hot));
}
