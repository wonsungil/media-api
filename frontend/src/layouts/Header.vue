<template>
  <el-menu :router="true"
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
  >
    <el-menu-item index="1" route="/">Home</el-menu-item>
    <el-sub-menu index="2">
      <template #title>Google</template>
      <el-sub-menu index="2-1">
        <template #title>광고계정</template>
        <el-menu-item index="2-1-1" route="/google/customer/list">List(EL)</el-menu-item>
        <el-menu-item index="2-1-2" route="/google/customer/list2">List(TUI)</el-menu-item>
        <el-menu-item index="2-1-3">item three</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="2-2">
        <template #title>캠페인</template>
        <el-menu-item index="2-2-1" route="/google/campaign/list">List</el-menu-item>
        <el-menu-item index="2-2-2">item two</el-menu-item>
        <el-menu-item index="2-2-3">item three</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="2-3">
        <template #title>광고그룹</template>
        <el-menu-item index="2-3-1" route="/google/adGroup/list">List</el-menu-item>
        <el-menu-item index="2-3-2">item two</el-menu-item>
        <el-menu-item index="2-3-3">item three</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="2-4">
        <template #title>광고</template>
        <el-menu-item index="2-4-1" route="/google/ad/list">List</el-menu-item>
        <el-menu-item index="2-4-2">item two</el-menu-item>
        <el-menu-item index="2-4-3">item three</el-menu-item>
      </el-sub-menu>

    </el-sub-menu>
    <el-menu-item index="3">Facebook</el-menu-item>
    <el-menu-item index="4">Kakao</el-menu-item>
  </el-menu>
</template>

<script>
import { defineComponent, ref } from 'vue'

export default defineComponent({
  setup() {
    const activeIndex = ref('1')
    const handleSelect = (item) => {
      console.log(item)
    }
    return {
      activeIndex,
      handleSelect,
    }
  },
})
</script>