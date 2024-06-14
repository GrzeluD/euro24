<template>
  <button v-if="localMatches.length < 4" @click="addMatch" class="text-center w-[428px] leading-[97px] h-[97px] border-blueBg border-2 rounded-2xl text-blueBg text-3xl font-heading">Dodaj mecz <span class="text-yellowColor">+</span></button>
  <template v-for="(match, index) in localMatches" :key="index">
    <div :class="colorArr[index]" class="text-blueBg px-6 items-center flex gap-8 w-[428px] leading-[97px] h-[97px] rounded-2xl relative">
      <div class="text-3xl font-heading">Mecz {{ index + 1 }}</div>
      <div class="h-[65px] flex flex-col">
        <span class="leading-[16px] text-sm block mb-2">Wynik</span>
        <input :class="colorArr[index]" class="text-center rounded-md w-[100px] h-[40px] border-blueBg border-[1px]" type="text" v-model="match.match" @input="updateMatch">
      </div>
      <div class="h-[65px] flex flex-col">
        <span class="leading-[16px] text-sm block mb-2">Karne *</span>
        <input :class="colorArr[index]" class="text-center rounded-md w-[100px] h-[40px] border-blueBg border-[1px]" type="text" v-model="match.actualPenaltyWinner" @input="updateMatch">
      </div>
      <span @click="deleteMatch(index)" class="leading-[0px] p-2 text-lg font-heading absolute text-blueDarkBg top-1 right-1 cursor-pointer">x</span>
    </div>
  </template>
</template>

<script>
export default {
  name: 'MatchWrapper',
  data() {
    return {
      localMatches: [],
      colorArr: ["bg-lightBlueBg", "bg-orangeBg", "bg-lightGreenBg", "bg-lightRedBg"]
    }
  },
  props: {
    matches: {
      type: Array,
    }
  },
  watch: {
    matches: {
      immediate: true,
      handler(newMatches) {
        this.localMatches = [...newMatches];
      }
    }
  },
  methods: {
    addMatch() {
      this.localMatches.push({
        match: '',
        actualPenaltyWinner: null,
      })
      this.emitMatches();
    },
    emitMatches() {
      this.$emit('update-matches', this.localMatches);
    },
    updateMatch() {
      this.emitMatches();
    },
    deleteMatch(index) {
      this.localMatches.splice(index, 1);
      this.emitMatches();
    },
  }
}
</script>

<style scoped>

</style>