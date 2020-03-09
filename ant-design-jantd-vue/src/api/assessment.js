import {axios} from '@/utils/request'

export function getAssessUserDetail(parameter) {
  return axios({
    url: 'http://192.168.43.160:8080/jantd-boot/teacher/staff/queryById',
    method: 'get',
    params: parameter
  })
}

export function getTeachProgress(parameter) {
  return axios({
    url: 'http://192.168.43.160:8080/jantd-boot/teacher/exam/queryExamByStaffid',
    method: 'get',
    params: parameter
  })
}

export function getExaminationPaper(parameter) {
  return axios({
    url: 'http://192.168.43.160:8080/jantd-boot/teacher/exam/queryExamByStaffid',
    method: 'get',
    params: parameter
  })
}

export function getExaminationAnalysis(parameter) {
  return axios({
    url: '',
    method: 'post',
    data: parameter
  })
}