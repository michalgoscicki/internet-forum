import {useLocalStorage} from "@vueuse/core";
import type {NewUser, User} from "~/types";
import {$fetchApi} from "~/composables/$fetchApi";

const emptyUser: User = {
    id: 0,
    username: "",
    email: "",
    password: "",
    name: "",
    surname: "",
    roles: {
        id: 0,
        name: "",
    },
};

export const useAuthStore = defineStore("auth", () => {
    const token = useLocalStorage("auth: token", "");
    const user = useLocalStorage("auth: user", emptyUser);

    const login = async (username: string, password: string) => {
        const data = await $fetchApi<{ accessToken: string; user: User }>("/api/v1/login", {
            method: "POST",
            body: {username, password},
        });
        console.log(data)
        if (data.accessToken) {
            token.value = data.accessToken;
            user.value = {
                id: data.user.id,
                username: data.user.username,
                email: data.user.email,
                password: "",
                name: data.user.name,
                surname: data.user.surname,
                roles: data.user.roles,
            };
        }
    };

    const register = async (newUser: NewUser) => {
        const data = await $fetchApi("/api/v1/register", {
            method: "POST",
            body: {
                email: newUser.email,
                name: newUser.name,
                surname: newUser.surname,
                username: newUser.username,
                password: newUser.password,
            },
        });
    };

    const logout = () => {
        token.value = "";
        user.value = emptyUser;
    };

    const updateProfile = async (updatedUser: User) => { // TODO: to chyba nie jest sprawdzone ani zaimplementowane
        if (!updatedUser.username.trim()) {
            updatedUser.username = user.value.username
        } else {
            updatedUser.username = updatedUser.username.trim()
        }
        if (!updatedUser.name.trim()) {
            updatedUser.name = user.value.name
        } else {
            updatedUser.name = updatedUser.name.trim()
        }
        if (!updatedUser.surname.trim()) {
            updatedUser.surname = user.value.surname
        } else {
            updatedUser.surname = updatedUser.surname.trim()
        }
        if (!updatedUser.email.trim()) {
            updatedUser.email = user.value.email
        } else {
            updatedUser.email = updatedUser.email.trim()
        }
        await $fetchApi("/api/profile", {
            method: "PUT",
            body: updatedUser,
        }).catch((err) => console.error(err));

        user.value = updatedUser;
    }

    const isLoggedIn = computed(() => token.value !== "");

    return {
        login,
        logout,
        register,
        updateProfile,
        isLoggedIn,
        user,
        token,
    };
});

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useAuthStore, import.meta.hot));
}
