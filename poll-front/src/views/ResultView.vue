<script setup lang="ts">

import ResultGraph from "@/components/ResultGraph.vue";
import ShareLink from "@/components/ShareLink.vue";
</script>

<template>
  <suspense>
    <ResultGraph/>
  </suspense>
  <ShareLink/>
</template>

<style scoped>

</style>