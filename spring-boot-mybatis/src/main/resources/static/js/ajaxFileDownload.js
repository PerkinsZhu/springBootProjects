/**
 * 报表数据下载
 */
function downloadScheduleReport() {
    //见 com.perkins.controller.UserController.downloadFile
    let url = "/download/xlsx?activityId=1&department=2&area=3";
    //ajax 下载文件
    $.ajax({
        url: url,
        type: 'get',
        data: {},
        success: function (res) {
            //返回的是base64字符串
            saveFile(res, '下载文件.xlsx');
        },
        error: function (err) {
            console.log(err)
        }
    });
}


function saveFile(base64, fileName) {
    base64ToBlob({b64data: base64, contentType: 'application/vnd.ms-excel'}).then(blob => {
        // 转后的blob对象
        if (window.navigator.msSaveOrOpenBlob) {
            navigator.msSaveBlob(blob, fileName)
        } else {
            //模拟 <a>的点击事件
            let link = document.createElement('a')
            link.href = window.URL.createObjectURL(blob)
            link.download = fileName //设置文件名字
            link.click()
            window.URL.revokeObjectURL(link.href)
        }
    })
}

//base64字符串转blob
function base64ToBlob({b64data = '', contentType = '', sliceSize = 512} = {}) {
    return new Promise((resolve, reject) => {
        // 使用 atob() 方法将数据解码
        let byteCharacters = atob(b64data);
        let byteArrays = [];
        for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
            let slice = byteCharacters.slice(offset, offset + sliceSize);
            let byteNumbers = [];
            for (let i = 0; i < slice.length; i++) {
                byteNumbers.push(slice.charCodeAt(i));
            }
            // 8 位无符号整数值的类型化数组。内容将初始化为 0。
            // 如果无法分配请求数目的字节，则将引发异常。
            byteArrays.push(new Uint8Array(byteNumbers));
        }
        let result = new Blob(byteArrays, {
            type: contentType
        })
        result = Object.assign(result, {
            // 这里一定要处理一下 URL.createObjectURL
            preview: URL.createObjectURL(result),
            name: `下载文件.xlsx`
        });
        //成功之后回调 blob
        resolve(result)
    })
}