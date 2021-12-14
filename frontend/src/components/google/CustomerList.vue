<template>
  <el-table :data="data" style="width: 100%">
    <el-table-column prop="id" label="id" width="180" />
    <el-table-column prop="descriptiveName" label="descriptiveName" width="180" />
    <el-table-column prop="currencyCode" label="currencyCode" />
    <el-table-column prop="hidden" label="hidden" />
    <el-table-column prop="level" label="level" sortable />
    <el-table-column prop="manager" label="manager" />
<!--    <el-table-column prop="resourceName" label="resourceName" />-->
    <el-table-column prop="timeZone" label="timeZone" />
    <el-table-column prop="testAccount" label="testAccount" />
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
      this.$axios.get('/api/google/customer/sub-customers')
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