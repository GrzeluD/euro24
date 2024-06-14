<template>
  <div class="w-[1200px] mx-auto">
    <button class="w-[240px] rounded-3xl text-center h-[48px] leading-[48px] bg-blueBg text-whiteColor" @click="addUser">Dodaj gracza</button>
    <template v-for="(user, index) in localUsers" :key="index">
    <div class="text-blueBg bg-userLightBlue items-center flex w-[428px] rounded-2xl relative w-full gap-4 mt-8 py-4 px-6">
        <div class="h-[65px] flex flex-col">
          <span class="leading-[16px] text-sm block mb-2"><span @click="deleteUser(index)" class="leading-[0px] pl-0 p-2 text-lg font-heading text-blueDarkBg cursor-pointer">x</span> Imię i nazwisko</span>
          <input class="text-center rounded-md w-[240px] h-[40px] border-blueBg border-[1px]" type="text" v-model="user.name" @input="updateUsers">
        </div>
        <div :class="colorArr[matchIndex]" v-for="(match, matchIndex) in matches" :key="matchIndex" class="py-2 px-4 flex flex-1 justify-center gap-4 rounded-lg">
          <div>
            <span class="leading-[16px] text-sm block mb-2">Mecz {{ matchIndex + 1 }}</span>
            <input class="text-center rounded-md w-[100px] h-[40px] border-blueBg border-[1px]" v-model="user.predictions[matchIndex]" type="text" @input="updateUsers">
          </div>
          <div>
            <span class="leading-[16px] text-sm block mb-2">Karne *</span>
            <input class="text-center rounded-md w-[56px] h-[40px] border-blueBg border-[1px]" v-model="user.penaltyPredictions[matchIndex]" type="text" @input="updateUsers">
          </div>
        </div>
        <div class="flex gap-6" v-if="matches.length === 0">
          <div>
            <span class="leading-[16px] text-sm block mb-2">Zwyciężca turnieju</span>
            <input type="checkbox" v-model="user.predictedWinner" @change="updateUsers" class="rounded-md w-[16px] h-[16px] border-blueBg border-[1px]">
          </div>
          <div>
            <span class="leading-[16px] text-sm block mb-2">Król strzelców</span>
            <input type="checkbox" v-model="user.predictedTopScorer" @change="updateUsers" class="rounded-md w-[16px] h-[16px] border-blueBg border-[1px]">
          </div>
          <div>
            <span class="leading-[16px] text-sm block mb-2">Najwięcej dokładnych wyników</span>
            <input type="checkbox" v-model="user.mostCorrectPredictions" @change="updateUsers" class="rounded-md w-[16px] h-[16px] border-blueBg border-[1px]">
          </div>
        </div>
      </div>
    </template>
    <button v-if="users.length > 0" @click="addUser" class="border rounded-3xl mt-6 mx-auto block w-[400px] text-center h-[48px] leading-[44px] text-blueBg">Dodaj kolejnego gracza</button>
  </div>
</template>

<script>
export default {
  name: 'UserWrapper',
  data() {
    return {
      localUsers: [],
      colorArr: ["bg-lightBlueBg", "bg-orangeBg", "bg-lightGreenBg", "bg-lightRedBg"]
    }
  },
  props: {
    users: {
      type: Array
    },
    matches: {
      type: Array
    }
  },
  watch: {
    users: {
      immediate: true,
      handler(newUsers) {
        this.localUsers = [...newUsers];
      }
    }
  },
  methods: {
    addUser() {
      this.localUsers.push({
        name: '',
        predictions: new Array(this.matches.length).fill(''),
        penaltyPredictions: new Array(this.matches.length).fill(''),
        predictedTopScorer: false,
        predictedWinner: false,
        mostCorrectPredictions: false,
      });
      this.emitUsers();
    },
    deleteUser(index) {
      this.localUsers.splice(index, 1);
      this.emitUsers();
    },
    emitUsers() {
      this.$emit('update-users', this.localUsers);
    },
    updateUsers() {
      this.emitUsers();
    },
  }
}
</script>

<style scoped>

</style>