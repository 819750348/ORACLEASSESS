import {axios} from '@/utils/request'

export function initLearningAndTrainingProjectManagement(parameter) {
  return axios({
    url: '/api/learningAndTrainingProjectManagement/queryPageList',
    method: 'get',
    params: parameter
  })
}


export function addLearningAndTrainingProjectManagement(parameter) {
  return axios({
    url: '/api/learningAndTrainingProjectManagement/addLearningAndTrainingProject',
    method: 'get',
    params: parameter
  })
}

export function editAndTrainingProjectManagement(parameter) {
  return axios({
    url: '/api/learningAndTrainingProjectManagement/editAndTrainingProjectManagement',
    method: 'get',
    params: parameter
  })
}