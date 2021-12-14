<template>
  <el-table :data="data" style="width: 100%">
    <el-table-column prop="id" label="id" width="180" />
    <el-table-column prop="name" label="name" width="180" />
    <el-table-column prop="status" label="status" />
    <el-table-column prop="startDate" label="startDate" />
    <el-table-column prop="endDate" label="endDate" />
    <el-table-column prop="campaignBudget" label="campaignBudget" />
    <el-table-column prop="servingStatus" label="servingStatus" />
    <el-table-column prop="resourceName" label="resourceName" />
    <el-table-column prop="biddingStrategyType" label="biddingStrategyType" />
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      data : null,
    }
  },
  mounted() {
    console.log("mounted");
    this.getCampaignList();
  },
  methods : {
    getCampaignList() {
      this.$axios.get('/api/google/campaign/allCampaigns/6583317631')
        .then(res => {
          console.log(res.data);
          this.data = res.data;
        }).catch(error => {
          console.log(error);
      })
    }
  }
}
</script>
<style lang="scss">
</style>