import {axios} from '@/utils/request'

//初始化制定试卷列表
export function toEditByExamAndStaff(parameter) {
  //console.log("aaaafafafa");
  return axios({
    url: '/teacher/exam/toEditByExamAndStaff',
    method: 'get',
    params: parameter
  })
}






