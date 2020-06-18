import {axios} from '@/utils/request'

export function initPaper(parameter) {
  return axios({
    url: '/api/paper/initPaper',
    method: 'get',
    params: parameter
  })
}
