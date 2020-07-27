package com.trs.devopsdemo.test;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Title Test
 * @Description:
 * @Create Date: 2020/7/22 10:48
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public class Test {


    public static void main(String[] args) {

        String s="[\n" +
                "  {\n" +
                "    \"index\": 0,\n" +
                "    \"name\": \"公共分类\",\n" +
                "    \"desc\": \"公共分类\",\n" +
                "    \"add_time\": 1594953095,\n" +
                "    \"up_time\": 1594953095,\n" +
                "    \"list\": [\n" +
                "      {\n" +
                "        \"query_path\": {\n" +
                "          \"path\": \"/apollo/user/getUsers\",\n" +
                "          \"params\": []\n" +
                "        },\n" +
                "        \"edit_uid\": 0,\n" +
                "        \"status\": \"undone\",\n" +
                "        \"type\": \"static\",\n" +
                "        \"req_body_is_json_schema\": true,\n" +
                "        \"res_body_is_json_schema\": true,\n" +
                "        \"api_opened\": false,\n" +
                "        \"index\": 0,\n" +
                "        \"tag\": [],\n" +
                "        \"_id\": 4898,\n" +
                "        \"method\": \"GET\",\n" +
                "        \"catid\": 1101,\n" +
                "        \"title\": \"分页获取所有用户\",\n" +
                "        \"path\": \"/apollo/user/getUsers\",\n" +
                "        \"project_id\": 309,\n" +
                "        \"req_params\": [],\n" +
                "        \"res_body_type\": \"json\",\n" +
                "        \"req_query\": [\n" +
                "          {\n" +
                "            \"required\": \"0\",\n" +
                "            \"_id\": \"5ebbb22613c9d518af201c73\",\n" +
                "            \"name\": \"pageNo\",\n" +
                "            \"example\": \"页数\",\n" +
                "            \"desc\": \"页面\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"required\": \"0\",\n" +
                "            \"_id\": \"5ebbb22613c9d518af201c72\",\n" +
                "            \"name\": \"pageSize\",\n" +
                "            \"example\": \"页面大小\",\n" +
                "            \"desc\": \"页面大小\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"req_headers\": [],\n" +
                "        \"req_body_form\": [],\n" +
                "        \"desc\": \"\",\n" +
                "        \"markdown\": \"\",\n" +
                "        \"res_body\": \"{\\\"$schema\\\":\\\"http://json-schema.org/draft-04/schema#\\\"," +
                "\\\"type\\\":\\\"object\\\",\\\"properties\\\":{\\\"resultCode\\\":{\\\"type\\\":\\\"object\\\"," +
                "\\\"properties\\\":{\\\"code\\\":{\\\"type\\\":\\\"string\\\"}," +
                "\\\"cnMsg\\\":{\\\"type\\\":\\\"string\\\"},\\\"enMsg\\\":{\\\"type\\\":\\\"string\\\"}}}," +
                "\\\"result\\\":{\\\"type\\\":\\\"object\\\"," +
                "\\\"properties\\\":{\\\"totalCount\\\":{\\\"type\\\":\\\"number\\\"}," +
                "\\\"pageSize\\\":{\\\"type\\\":\\\"number\\\"},\\\"data\\\":{\\\"type\\\":\\\"array\\\"," +
                "\\\"items\\\":{\\\"type\\\":\\\"object\\\"," +
                "\\\"properties\\\":{\\\"id\\\":{\\\"type\\\":\\\"number\\\"}," +
                "\\\"tel\\\":{\\\"type\\\":\\\"string\\\"},\\\"realName\\\":{\\\"type\\\":\\\"string\\\"}," +
                "\\\"email\\\":{\\\"type\\\":\\\"null\\\"}},\\\"required\\\":[\\\"id\\\",\\\"tel\\\"," +
                "\\\"realName\\\",\\\"email\\\"]}}}}}}\",\n" +
                "        \"uid\": 491,\n" +
                "        \"add_time\": 1595208589,\n" +
                "        \"up_time\": 1595215736,\n" +
                "        \"__v\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"query_path\": {\n" +
                "          \"path\": \"/devops/inner/toAthena/getApplicationResponseAvg\",\n" +
                "          \"params\": []\n" +
                "        },\n" +
                "        \"edit_uid\": 0,\n" +
                "        \"status\": \"undone\",\n" +
                "        \"type\": \"static\",\n" +
                "        \"req_body_is_json_schema\": true,\n" +
                "        \"res_body_is_json_schema\": true,\n" +
                "        \"api_opened\": false,\n" +
                "        \"index\": 0,\n" +
                "        \"tag\": [\n" +
                "          \"公共分类\"\n" +
                "        ],\n" +
                "        \"_id\": 4818,\n" +
                "        \"method\": \"POST\",\n" +
                "        \"title\": \"各应用平均响应时间\",\n" +
                "        \"desc\": \"请求体\\n{\\\"duration\\\":1,\\\"appIds\\\":[434232,2]}\",\n" +
                "        \"path\": \"/devops/inner/toAthena/getApplicationResponseAvg \",\n" +
                "        \"req_params\": [],\n" +
                "        \"req_body_form\": [],\n" +
                "        \"req_headers\": [\n" +
                "          {\n" +
                "            \"required\": \"1\",\n" +
                "            \"_id\": \"5f14f350a4860c32ff211f7b\",\n" +
                "            \"name\": \"Content-Type\",\n" +
                "            \"value\": \"application/json\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"required\": \"0\",\n" +
                "            \"_id\": \"5f14f350a4860c32ff211f7a\",\n" +
                "            \"name\": \"X-User-Token\",\n" +
                "            \"desc\": \"X-User-Token (Only:undefined)\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"req_query\": [],\n" +
                "        \"req_body_type\": \"json\",\n" +
                "        \"res_body_type\": \"json\",\n" +
                "        \"res_body\": \"{\\n  \\\"$schema\\\": \\\"http://json-schema.org/draft-04/schema#\\\",\\n  " +
                "\\\"type\\\": \\\"object\\\",\\n  \\\"properties\\\": {\\n    \\\"resultCode\\\": {\\n      " +
                "\\\"type\\\": \\\"object\\\",\\n      \\\"properties\\\": {\\n        \\\"code\\\": {\\n          " +
                "\\\"type\\\": \\\"string\\\"\\n        },\\n        \\\"cnMsg\\\": {\\n          \\\"type\\\": " +
                "\\\"string\\\"\\n        },\\n        \\\"enMsg\\\": {\\n          \\\"type\\\": \\\"string\\\"\\n  " +
                "      }\\n      }\\n    },\\n    \\\"result\\\": {\\n      \\\"type\\\": \\\"object\\\",\\n      " +
                "\\\"properties\\\": {\\n        \\\"totalCount\\\": {\\n          \\\"type\\\": \\\"number\\\"\\n   " +
                "     },\\n        \\\"pageSize\\\": {\\n          \\\"type\\\": \\\"number\\\"\\n        },\\n      " +
                "  \\\"data\\\": {\\n          \\\"type\\\": \\\"array\\\",\\n          \\\"items\\\": {\\n          " +
                "  \\\"type\\\": \\\"object\\\",\\n            \\\"properties\\\": {\\n              " +
                "\\\"applicationId\\\": {\\n                \\\"type\\\": \\\"number\\\",\\n                " +
                "\\\"description\\\": \\\"ID\\\"\\n              },\\n              \\\"applicationName\\\": {\\n    " +
                "            \\\"type\\\": \\\"string\\\",\\n                \\\"description\\\": \\\"名称\\\"\\n      " +
                "        },\\n              \\\"percent\\\": {\\n                \\\"type\\\": \\\"string\\\"\\n     " +
                "         },\\n              \\\"count\\\": {\\n                \\\"type\\\": \\\"number\\\",\\n     " +
                "           \\\"description\\\": \\\"总数\\\"\\n              },\\n              " +
                "\\\"responseDuration\\\": {\\n                \\\"type\\\": \\\"number\\\",\\n                " +
                "\\\"description\\\": \\\"总响应时间\\\"\\n              },\\n              \\\"responseTime\\\": {\\n    " +
                "            \\\"type\\\": \\\"number\\\",\\n                \\\"description\\\": \\\"平均响应时间\\\"\\n  " +
                "            }\\n            }\\n          }\\n        }\\n      }\\n    }\\n  }\\n}\",\n" +
                "        \"project_id\": 309,\n" +
                "        \"catid\": 1101,\n" +
                "        \"uid\": 491,\n" +
                "        \"add_time\": 1595208528,\n" +
                "        \"up_time\": 1595208528,\n" +
                "        \"__v\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"query_path\": {\n" +
                "          \"path\": \"/devops/inner/toAthena/getLongResponseApiDistribution\",\n" +
                "          \"params\": []\n" +
                "        },\n" +
                "        \"edit_uid\": 0,\n" +
                "        \"status\": \"undone\",\n" +
                "        \"type\": \"static\",\n" +
                "        \"req_body_is_json_schema\": true,\n" +
                "        \"res_body_is_json_schema\": true,\n" +
                "        \"api_opened\": false,\n" +
                "        \"index\": 0,\n" +
                "        \"tag\": [\n" +
                "          \"公共分类\"\n" +
                "        ],\n" +
                "        \"_id\": 4822,\n" +
                "        \"method\": \"POST\",\n" +
                "        \"title\": \"慢响应API分布 按百分比顺序排序\",\n" +
                "        \"desc\": \"请求体\\n\\n```\\n{\\n    \\\"duration\\\": 1,\\n    \\\"appIds\\\": [\\n        " +
                "4343,\\n        2\\n    ],\\n    \\\"pageNo\\\":1,\\n    " +
                "\\\"pageSize\\\":20\\n}\\n```\\n\\n返回体\\n\\n```\\n{\\n  \\\"resultCode\\\": {\\n    \\\"code\\\": " +
                "\\\"00000\\\",\\n    \\\"cnMsg\\\": \\\"成功\\\",\\n    \\\"enMsg\\\": \\\"sucess\\\"\\n  },\\n  " +
                "\\\"result\\\": {\\n    \\\"totalCount\\\": 1,\\n    \\\"pageSize\\\": 20,\\n    \\\"data\\\": [\\n " +
                "     {\\n        \\\"allDisableCout\\\": 436,\\n        \\\"vos\\\": [\\n          {\\n            " +
                "\\\"applicationId\\\": 4343,\\n            \\\"applicationName\\\": \\\"拓尔思\\\",\\n            " +
                "\\\"percent\\\": \\\"100%\\\",\\n            \\\"count\\\": 436,\\n            " +
                "\\\"responseDuration\\\": null,\\n            \\\"responseTime\\\": null\\n          }\\n        " +
                "]\\n      }\\n    ]\\n  }\\n}\\n```\",\n" +
                "        \"path\": \"/devops/inner/toAthena/getLongResponseApiDistribution\",\n" +
                "        \"req_params\": [],\n" +
                "        \"req_body_form\": [],\n" +
                "        \"req_headers\": [\n" +
                "          {\n" +
                "            \"required\": \"1\",\n" +
                "            \"_id\": \"5f14f350a4860c32ff211f7d\",\n" +
                "            \"name\": \"Content-Type\",\n" +
                "            \"value\": \"application/json\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"required\": \"0\",\n" +
                "            \"_id\": \"5f14f350a4860c32ff211f7c\",\n" +
                "            \"name\": \"X-User-Token\",\n" +
                "            \"desc\": \"X-User-Token (Only:undefined)\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"req_query\": [],\n" +
                "        \"req_body_type\": \"json\",\n" +
                "        \"res_body_type\": \"json\",\n" +
                "        \"res_body\": \"{\\n  \\\"$schema\\\": \\\"http://json-schema.org/draft-04/schema#\\\",\\n  " +
                "\\\"type\\\": \\\"object\\\",\\n  \\\"properties\\\": {\\n    \\\"resultCode\\\": {\\n      " +
                "\\\"type\\\": \\\"object\\\",\\n      \\\"properties\\\": {\\n        \\\"code\\\": {\\n          " +
                "\\\"type\\\": \\\"string\\\"\\n        },\\n        \\\"cnMsg\\\": {\\n          \\\"type\\\": " +
                "\\\"string\\\"\\n        },\\n        \\\"enMsg\\\": {\\n          \\\"type\\\": \\\"string\\\"\\n  " +
                "      }\\n      }\\n    },\\n    \\\"result\\\": {\\n      \\\"type\\\": \\\"object\\\",\\n      " +
                "\\\"properties\\\": {\\n        \\\"totalCount\\\": {\\n          \\\"type\\\": \\\"number\\\"\\n   " +
                "     },\\n        \\\"pageSize\\\": {\\n          \\\"type\\\": \\\"number\\\"\\n        },\\n      " +
                "  \\\"data\\\": {\\n          \\\"type\\\": \\\"array\\\",\\n          \\\"items\\\": {\\n          " +
                "  \\\"type\\\": \\\"object\\\",\\n            \\\"properties\\\": {\\n              " +
                "\\\"allDisableCout\\\": {\\n                \\\"type\\\": \\\"number\\\",\\n                " +
                "\\\"description\\\": \\\"饼图中间总数\\\"\\n              },\\n              \\\"vos\\\": {\\n            " +
                "    \\\"type\\\": \\\"array\\\",\\n                \\\"items\\\": {\\n                  " +
                "\\\"type\\\": \\\"object\\\",\\n                  \\\"properties\\\": {\\n                    " +
                "\\\"applicationId\\\": {\\n                      \\\"type\\\": \\\"number\\\",\\n                   " +
                "   \\\"description\\\": \\\"ID\\\"\\n                    },\\n                    " +
                "\\\"applicationName\\\": {\\n                      \\\"type\\\": \\\"string\\\",\\n                 " +
                "     \\\"description\\\": \\\"名称\\\"\\n                    },\\n                    \\\"percent\\\":" +
                " {\\n                      \\\"type\\\": \\\"string\\\",\\n                      " +
                "\\\"description\\\": \\\"百分比\\\"\\n                    },\\n                    \\\"count\\\": {\\n " +
                "                     \\\"type\\\": \\\"number\\\",\\n                      \\\"description\\\": " +
                "\\\"次数\\\"\\n                    },\\n                    \\\"responseDuration\\\": {\\n            " +
                "          \\\"type\\\": \\\"integer\\\",\\n                      \\\"description\\\": " +
                "\\\"响应周期\\\"\\n                    },\\n                    \\\"responseTime\\\": {\\n              " +
                "        \\\"type\\\": \\\"integer\\\"\\n                    }\\n                  }\\n              " +
                "  },\\n                \\\"description\\\": \\\"各应用的详情\\\"\\n              }\\n            }\\n     " +
                "     }\\n        }\\n      }\\n    }\\n  }\\n}\",\n" +
                "        \"req_body_other\": \"{\\n  \\\"type\\\": \\\"object\\\",\\n  \\\"title\\\": \\\"empty " +
                "object\\\",\\n  \\\"properties\\\": {}\\n}\",\n" +
                "        \"project_id\": 309,\n" +
                "        \"catid\": 1101,\n" +
                "        \"uid\": 491,\n" +
                "        \"add_time\": 1595208528,\n" +
                "        \"up_time\": 1595208528,\n" +
                "        \"__v\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"query_path\": {\n" +
                "          \"path\": \"/devops/inner/toAthena/getLongResponseApiByPage\",\n" +
                "          \"params\": []\n" +
                "        },\n" +
                "        \"edit_uid\": 0,\n" +
                "        \"status\": \"undone\",\n" +
                "        \"type\": \"static\",\n" +
                "        \"req_body_is_json_schema\": true,\n" +
                "        \"res_body_is_json_schema\": true,\n" +
                "        \"api_opened\": false,\n" +
                "        \"index\": 0,\n" +
                "        \"tag\": [\n" +
                "          \"公共分类\"\n" +
                "        ],\n" +
                "        \"_id\": 4826,\n" +
                "        \"method\": \"POST\",\n" +
                "        \"title\": \"查询慢响应API列表 按平均响应时间倒序返回数据\",\n" +
                "        \"desc\": \"请求体:\\n\\n```\\n{\\n    \\\"duration\\\": 1,\\n    \\\"appIds\\\": [\\n        " +
                "4343,\\n        2\\n    ],\\n    \\\"apiName\\\": \\\"\\\",\\n    \\\"pageNo\\\":1,\\n    " +
                "\\\"pageSize\\\":20\\n}\\n```\\n\\n返回体\\n\\n```\\n{\\n  \\\"resultCode\\\": {\\n    \\\"code\\\": " +
                "\\\"00000\\\",\\n    \\\"cnMsg\\\": \\\"成功\\\",\\n    \\\"enMsg\\\": \\\"sucess\\\"\\n  },\\n  " +
                "\\\"result\\\": {\\n    \\\"totalCount\\\": 1,\\n    \\\"pageSize\\\": 20,\\n    \\\"data\\\": " +
                "[\\n      {\\n        \\\"calleeApiId\\\": 434232,\\n        \\\"calleeApi\\\": " +
                "\\\"resultsGenerator\\\",\\n        \\\"calleeServiceId\\\": 543232,\\n        " +
                "\\\"calleeService\\\": \\\"稿件智能编辑\\\",\\n        \\\"applicationId\\\": 4343,\\n        " +
                "\\\"applicationName\\\": \\\"拓尔思\\\",\\n        \\\"responseDuration\\\": 71760,\\n        " +
                "\\\"count\\\": 368,\\n        \\\"responseTime\\\": 195,\\n        \\\"newCallTime\\\": " +
                "\\\"2020-04-21 11:20:47.802413\\\"\\n      }\\n    ]\\n  }\\n}\\n```\",\n" +
                "        \"path\": \"/devops/inner/toAthena/getLongResponseApiByPage\",\n" +
                "        \"req_params\": [],\n" +
                "        \"req_body_form\": [],\n" +
                "        \"req_headers\": [\n" +
                "          {\n" +
                "            \"required\": \"1\",\n" +
                "            \"_id\": \"5f14f350a4860c32ff211f7e\",\n" +
                "            \"name\": \"Content-Type\",\n" +
                "            \"value\": \"application/json\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"req_query\": [],\n" +
                "        \"req_body_type\": \"json\",\n" +
                "        \"res_body_type\": \"json\",\n" +
                "        \"res_body\": \"{\\n  \\\"$schema\\\": \\\"http://json-schema.org/draft-04/schema#\\\",\\n  " +
                "\\\"type\\\": \\\"object\\\",\\n  \\\"properties\\\": {\\n    \\\"resultCode\\\": {\\n      " +
                "\\\"type\\\": \\\"object\\\",\\n      \\\"properties\\\": {\\n        \\\"code\\\": {\\n          " +
                "\\\"type\\\": \\\"string\\\"\\n        },\\n        \\\"cnMsg\\\": {\\n          \\\"type\\\": " +
                "\\\"string\\\"\\n        },\\n        \\\"enMsg\\\": {\\n          \\\"type\\\": \\\"string\\\"\\n  " +
                "      }\\n      }\\n    },\\n    \\\"result\\\": {\\n      \\\"type\\\": \\\"object\\\",\\n      " +
                "\\\"properties\\\": {\\n        \\\"totalCount\\\": {\\n          \\\"type\\\": \\\"number\\\",\\n  " +
                "        \\\"description\\\": \\\"总数\\\"\\n        },\\n        \\\"pageSize\\\": {\\n          " +
                "\\\"type\\\": \\\"number\\\"\\n        },\\n        \\\"data\\\": {\\n          \\\"type\\\": " +
                "\\\"array\\\",\\n          \\\"items\\\": {\\n            \\\"type\\\": \\\"object\\\",\\n          " +
                "  \\\"properties\\\": {\\n              \\\"calleeApiId\\\": {\\n                \\\"type\\\": " +
                "\\\"number\\\"\\n              },\\n              \\\"calleeApi\\\": {\\n                " +
                "\\\"type\\\": \\\"string\\\",\\n                \\\"description\\\": \\\"API名称\\\"\\n              " +
                "},\\n              \\\"calleeServiceId\\\": {\\n                \\\"type\\\": \\\"number\\\"\\n     " +
                "         },\\n              \\\"calleeService\\\": {\\n                \\\"type\\\": \\\"string\\\"," +
                "\\n                \\\"description\\\": \\\"所属服务\\\"\\n              },\\n              " +
                "\\\"applicationId\\\": {\\n                \\\"type\\\": \\\"number\\\"\\n              },\\n       " +
                "       \\\"applicationName\\\": {\\n                \\\"type\\\": \\\"string\\\",\\n                " +
                "\\\"description\\\": \\\"所属应用\\\"\\n              },\\n              \\\"responseDuration\\\": {\\n " +
                "               \\\"type\\\": \\\"number\\\"\\n              },\\n              \\\"count\\\": {\\n  " +
                "              \\\"type\\\": \\\"number\\\",\\n                \\\"description\\\": \\\"被调用次数\\\"\\n " +
                "             },\\n              \\\"responseTime\\\": {\\n                \\\"type\\\": " +
                "\\\"number\\\",\\n                \\\"description\\\": \\\"平均响应时间\\\"\\n              },\\n         " +
                "     \\\"newCallTime\\\": {\\n                \\\"type\\\": \\\"string\\\",\\n                " +
                "\\\"description\\\": \\\"最新调用时间\\\"\\n              }\\n            }\\n          }\\n        }\\n  " +
                "    }\\n    }\\n  }\\n}\",\n" +
                "        \"req_body_other\": \"{\\n  \\\"$schema\\\": \\\"http://json-schema" +
                ".org/draft-04/schema#\\\",\\n  \\\"type\\\": \\\"object\\\",\\n  \\\"properties\\\": {\\n    " +
                "\\\"duration\\\": {\\n      \\\"type\\\": \\\"number\\\"\\n    },\\n    \\\"appIds\\\": {\\n      " +
                "\\\"type\\\": \\\"array\\\",\\n      \\\"items\\\": {\\n        \\\"type\\\": \\\"number\\\"\\n     " +
                " }\\n    },\\n    \\\"apiName\\\": {\\n      \\\"type\\\": \\\"string\\\"\\n    },\\n    " +
                "\\\"pageNo\\\": {\\n      \\\"type\\\": \\\"number\\\"\\n    },\\n    \\\"pageSize\\\": {\\n      " +
                "\\\"type\\\": \\\"number\\\"\\n    }\\n  }\\n}\",\n" +
                "        \"project_id\": 309,\n" +
                "        \"catid\": 1101,\n" +
                "        \"uid\": 491,\n" +
                "        \"add_time\": 1595208528,\n" +
                "        \"up_time\": 1595208528,\n" +
                "        \"__v\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"query_path\": {\n" +
                "          \"path\": \"/devops/inner/toAthena/countLongResponseApi\",\n" +
                "          \"params\": []\n" +
                "        },\n" +
                "        \"edit_uid\": 0,\n" +
                "        \"status\": \"undone\",\n" +
                "        \"type\": \"static\",\n" +
                "        \"req_body_is_json_schema\": true,\n" +
                "        \"res_body_is_json_schema\": true,\n" +
                "        \"api_opened\": false,\n" +
                "        \"index\": 0,\n" +
                "        \"tag\": [\n" +
                "          \"公共分类\"\n" +
                "        ],\n" +
                "        \"_id\": 4830,\n" +
                "        \"method\": \"POST\",\n" +
                "        \"title\": \"查询慢响应API数量\",\n" +
                "        \"desc\": \"请求json:\\n{\\\"duration\\\":1,\\\"appIds\\\":[1,2]}\",\n" +
                "        \"path\": \"/devops/inner/toAthena/countLongResponseApi\",\n" +
                "        \"req_params\": [],\n" +
                "        \"req_body_form\": [],\n" +
                "        \"req_headers\": [\n" +
                "          {\n" +
                "            \"required\": \"1\",\n" +
                "            \"_id\": \"5f14f350a4860c32ff211f80\",\n" +
                "            \"name\": \"Content-Type\",\n" +
                "            \"value\": \"application/json\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"required\": \"0\",\n" +
                "            \"_id\": \"5f14f350a4860c32ff211f7f\",\n" +
                "            \"name\": \"X-User-Token\",\n" +
                "            \"desc\": \"X-User-Token (Only:undefined)\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"req_query\": [],\n" +
                "        \"req_body_type\": \"json\",\n" +
                "        \"res_body_type\": \"json\",\n" +
                "        \"res_body\": \"{\\n  \\\"type\\\": \\\"object\\\",\\n  \\\"title\\\": \\\"empty object\\\"," +
                "\\n  \\\"properties\\\": {}\\n}\",\n" +
                "        \"req_body_other\": \"{\\n  \\\"type\\\": \\\"object\\\",\\n  \\\"title\\\": \\\"empty " +
                "object\\\",\\n  \\\"properties\\\": {}\\n}\",\n" +
                "        \"project_id\": 309,\n" +
                "        \"catid\": 1101,\n" +
                "        \"uid\": 491,\n" +
                "        \"add_time\": 1595208528,\n" +
                "        \"up_time\": 1595208528,\n" +
                "        \"__v\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"query_path\": {\n" +
                "          \"path\": \"/apollo/user/fuzzyQueryUsers\",\n" +
                "          \"params\": []\n" +
                "        },\n" +
                "        \"edit_uid\": 0,\n" +
                "        \"status\": \"undone\",\n" +
                "        \"type\": \"static\",\n" +
                "        \"req_body_is_json_schema\": true,\n" +
                "        \"res_body_is_json_schema\": true,\n" +
                "        \"api_opened\": false,\n" +
                "        \"index\": 0,\n" +
                "        \"tag\": [],\n" +
                "        \"_id\": 4902,\n" +
                "        \"method\": \"GET\",\n" +
                "        \"catid\": 1101,\n" +
                "        \"title\": \"模糊查询用户\",\n" +
                "        \"path\": \"/apollo/user/fuzzyQueryUsers\",\n" +
                "        \"project_id\": 309,\n" +
                "        \"req_params\": [],\n" +
                "        \"res_body_type\": \"json\",\n" +
                "        \"req_query\": [\n" +
                "          {\n" +
                "            \"required\": \"1\",\n" +
                "            \"_id\": \"5ebbb6e313c9d518af201c76\",\n" +
                "            \"name\": \"name\",\n" +
                "            \"example\": \"名字\",\n" +
                "            \"desc\": \"姓名\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"required\": \"0\",\n" +
                "            \"_id\": \"5ebbb6e313c9d518af201c75\",\n" +
                "            \"name\": \"pageNo\",\n" +
                "            \"example\": \"页数\",\n" +
                "            \"desc\": \"当前页数\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"required\": \"0\",\n" +
                "            \"_id\": \"5ebbb6e313c9d518af201c74\",\n" +
                "            \"name\": \"pageSize\",\n" +
                "            \"example\": \"数据大小\",\n" +
                "            \"desc\": \"\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"req_headers\": [],\n" +
                "        \"req_body_form\": [],\n" +
                "        \"desc\": \"\",\n" +
                "        \"markdown\": \"\",\n" +
                "        \"res_body\": \"{\\\"type\\\":\\\"object\\\",\\\"title\\\":\\\"empty object\\\"," +
                "\\\"properties\\\":{}}\",\n" +
                "        \"uid\": 491,\n" +
                "        \"add_time\": 1595208589,\n" +
                "        \"up_time\": 1595215736,\n" +
                "        \"__v\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"query_path\": {\n" +
                "          \"path\": \"/user/verifyCipher\",\n" +
                "          \"params\": []\n" +
                "        },\n" +
                "        \"edit_uid\": 0,\n" +
                "        \"status\": \"undone\",\n" +
                "        \"type\": \"static\",\n" +
                "        \"req_body_is_json_schema\": true,\n" +
                "        \"res_body_is_json_schema\": true,\n" +
                "        \"api_opened\": false,\n" +
                "        \"index\": 0,\n" +
                "        \"tag\": [],\n" +
                "        \"_id\": 4906,\n" +
                "        \"method\": \"POST\",\n" +
                "        \"catid\": 1101,\n" +
                "        \"title\": \"验证密码\",\n" +
                "        \"path\": \"/user/verifyCipher\",\n" +
                "        \"project_id\": 309,\n" +
                "        \"req_params\": [],\n" +
                "        \"res_body_type\": \"json\",\n" +
                "        \"req_query\": [\n" +
                "          {\n" +
                "            \"required\": \"1\",\n" +
                "            \"_id\": \"5ec22a3613c9d518af201d15\",\n" +
                "            \"name\": \"cipher\",\n" +
                "            \"example\": \"密码\",\n" +
                "            \"desc\": \"Base64后\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"req_headers\": [\n" +
                "          {\n" +
                "            \"required\": \"1\",\n" +
                "            \"_id\": \"5ec22a3613c9d518af201d17\",\n" +
                "            \"name\": \"Content-Type\",\n" +
                "            \"value\": \"application/json\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"required\": \"1\",\n" +
                "            \"_id\": \"5ec22a3613c9d518af201d16\",\n" +
                "            \"name\": \"X-User-Token\",\n" +
                "            \"value\": \"61d75f761ad64ab0975d72f10af704a1\",\n" +
                "            \"example\": \"61d75f761ad64ab0975d72f10af704a1\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"req_body_form\": [],\n" +
                "        \"desc\": \"\",\n" +
                "        \"markdown\": \"\",\n" +
                "        \"res_body\": \"{\\\"type\\\":\\\"object\\\",\\\"title\\\":\\\"empty object\\\"," +
                "\\\"properties\\\":{}}\",\n" +
                "        \"req_body_type\": \"json\",\n" +
                "        \"req_body_other\": \"{\\\"$schema\\\":\\\"http://json-schema.org/draft-04/schema#\\\"," +
                "\\\"type\\\":\\\"object\\\",\\\"properties\\\":{\\\"cipher\\\":{\\\"type\\\":\\\"string\\\"}}}\",\n" +
                "        \"uid\": 491,\n" +
                "        \"add_time\": 1595208590,\n" +
                "        \"up_time\": 1595215736,\n" +
                "        \"__v\": 0\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"index\": 0,\n" +
                "    \"name\": \"自动化测试\",\n" +
                "    \"desc\": null,\n" +
                "    \"add_time\": 1594953147,\n" +
                "    \"up_time\": 1594953147,\n" +
                "    \"list\": [\n" +
                "      \n" +
                "     \n" +
                "    ]\n" +
                "  }\n" +
                "]";

        List<ApiInfo> apis= JSONObject.parseArray(s,ApiInfo.class);
        System.out.println(apis.get(0).getList().get(0));


    }
}
