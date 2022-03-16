// state
export const state = () => ({
    token: localStorage.getItem('token') || ''
});

// mutations : updates the state
export const mutations = {
    SET_USER_TOKEN(state, token){
        state.token = token;
    },

    logout(state){
        state.token = '';
    }
}

// actions : async (ex: api call, THEN change the state)
export const actions = {
    setToken({commit}, newValue) {
        commit("SET_USER_TOKEN", newValue);
    },

}

// getters