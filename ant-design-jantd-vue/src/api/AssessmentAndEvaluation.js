import {axios} from '@/utils/request'

//初始化制定试卷列表
export function initQueryExamPageList(parameter) {
  //console.log("aaaafafafa");
  return axios({
    url: '/teacher/exam/queryExamPageList',
    method: 'get',
    params: parameter
  })
}
//初始化考核评价列表
export function initEvaluationList() {
  return axios({
    url: '/teacher/exam/queryExamCopyPageList',
    method: 'get'
  })
}
//试卷推送
export function sendExamById(parameter) {
  return axios({
    url: '/teacher/exam/sendExam',
    method: 'post',
    params: parameter
  })
}

export function initPaper(parameter) {
  return axios({
    url: '/teacher/exam/detailExamById',
    method: 'get',
    params: parameter
  })
}





