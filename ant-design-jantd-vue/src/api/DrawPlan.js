import {axios} from '@/utils/request'

//初始化课表配置列表
export function initCourseConfigurationData(parameter) {
  //console.log("aaaafafafa");
  return axios({
    url: '/teacher/studyCourse/queryPageList',
    method: 'get',
    params: parameter
  })
}

//批量删除课表
export function deleteStudyCourseData(parameter) {
  //console.log("aaaafafafa");
  return axios({
    url: '/teacher/studyCourse/deleteBatch',
    method: 'post',
    params: parameter
  })
}
//初始项目下拉列表
export function getStudySelect() {
  return axios({
    url: '/teacher/study/getStudySelect',
    method: 'get'
  })
}

//保存计划提交
export function editStudyCourse(parameter) {
  return axios({
    url: '/teacher/studyCourse/edit',
    method: 'post',
    params: parameter
  })
}

//推送课程
export function sendCourse() {
  return axios({
    url: '/teacher/studyCourse/toStudents',
    method: 'post'
  })
}


//学习和训练进度列表
export function initRateQueryPageList(parameter) {
  return axios({
    url: '/teacher/studyCourse/rateQueryPageList',
    method: 'get',
    params: parameter
  })
}
//学习和训练进度根据项目查询人员列表
export function initPersonalCompletionData(parameter) {
  return axios({
    url: '/teacher/studyCourse/staffQueryPageList',
    method: 'get',
    params: parameter
  })
}
//学习和训练进度列表
export function createExam() {
  return axios({
    url: '/teacher/exam/createExam',
    method: 'post'
  })
}

