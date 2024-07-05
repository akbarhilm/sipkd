/*$(document).ready(function () {
    var timer = $.timer(function () {
        var idskpd = $("#idskpd").val();
        var level = $("#level").val();
        if (idskpd && idskpd != 'undefined' && $.trim(idskpd).length > 0) {
            gridsppup();
        }
    });
   // timer.set({time: 120000, autostart: true});
});*/

function gridsppup() {
     var baseurl = getbasepath()
    var urljson = baseurl + "/sp2dsah/json/getlistsp2d";
    if (typeof myTable == 'undefined') {
        myTable = $('#btlspdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
               // $(".checkbox").hide();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                 {name: "kodeSkpd", value: $('#kodeSkpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "levelSkpd", value: $('#levelSkpd').val()},
                {name: "kproses", value: $('#kproses').val()}        
                );
            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var kskpd = $('#kodeskpd').val();
                var iskpd = $('#idskpd').val();
                var lskpd = $('#skpd').val();
                var objar=lskpd.split("/");
                var ls = objar[2];
                var tahun = $('#tahun').val();
                var kj = aData['kodeJenis'];
                var c = aData['kodeJenis'];
                var donlod = "-";
                var batal = "-";
                var cekprint = "-";
                var namafile = "SP2D"+"-"+kj+"-"+aData['kodeBeban']+"-"+tahun+"-"+aData['sp2d']['nomorSp2d'] +".pdf"; 
                var stat = aData['sp2d']['kodeCetak'];
                var nilaisp2d = "<input type='hidden'    id='nilaisp2d" + aData['id'] + "' value='" + aData['sp2d']['nilaiSp2d'] + "'  />";  
                 var nomorsp2d = "<input type='hidden'    id='nomorsp2d" + aData['id'] + "' value='" + aData['sp2d']['nomorSp2d'] + "'  />";  
               //var donlod = aData['status'] == 'CETAK' || aData['status'] == 'VALIDASI' ? "<a href='" + getbasepath() + "/cetaksp2d/" + aData['kodeBeban'] +"/" + aData['id'] + "/" + aData['noSp2d'] + "' class='fancybox fancybox.iframe icon-book' ></a>" : '';
             // alert(stat);
                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(aData['spmUp']['noSpm']);
                $('td:eq(2)', nRow).html(aData['sp2d']['nomorSp2d'] + nomorsp2d + nilaisp2d);
                $('td:eq(3)', nRow).html(aData['spmUp']['tglSpm']);
                $('td:eq(4)', nRow).html(aData['sp2d']['tglSp2d']);
                $('td:eq(5)', nRow).html(aData['sp2d']['kodeCetakPotongan']);
                $('td:eq(6)', nRow).html(aData['kodeBeban']);
                $('td:eq(7)', nRow).html(aData['kodeJenis']);
                $('td:eq(8)', nRow).html(accounting.formatNumber(aData['sp2d']['nilaiSp2d']));
                 var namaPptk = "<input type='hidden'    id='namaPptk" + aData['id'] + "' value='" + aData['namaPptk'] + "'  />"; 
                 var nipPptk = "<input type='hidden'    id='nipPptk" + aData['id'] + "' value='" + aData['nipPptk'] + "'  />";  
                 
                 //alert(nilaisp2d);
                 //alert(nomorsp2d);
                 
                $('td:eq(9)', nRow).html(aData['sp2d']['kodeCetak']);
                $('td:eq(10)', nRow).html("<span id='namaPptk" + aData['id'] + "'>" + aData['namaPptk'] + nipPptk + "</span>");
                //$('td:eq(10)', nRow).html(aData['status_cetakpotongan']);tmsp2d.c_sp2d_cetak
               if (stat == 'CETAK'){
                 var cekprint = "<input type='checkbox' name='cek" + aData['id'] + "'  id='cek" + aData['id'] + "' value='" + aData['id'] + "' class='checkbox' />";  
               }
                
              //   alert(cekprint);
             //   if (stat == 'BELUM CETAK') {
            //       cekprint = "<input type='checkbox' name='cek" + aData['id'] + "'  id='cek" + aData['id'] + "' value='" + aData['id'] + "' class='checkbox' />";
            //    } else if (stat == 'CETAK')
          //      {
         //           donlod = "<a class='fancybox fancybox.iframe icon-book' href='" + getbasepath() + "/cetaksp2d/" + aData['kodeBeban'] + "/"+ aData['kodeJenis'] +"/"+ aData['id'] + "/" + aData['sp2d']['nomorSp2d'] + "/"+iskpd+ "/" +ls+ "/"+namafile + "' ></a>";
        //             batal = "<a class='icon-remove linkpilihan' href='#' onclick=batalspd(" + aData['id'] + ",'" + aData['sp2d']['nomorSp2d'] + "') ></a>";
        //            cekprint = "-";
                   
       //       }
                $('td:eq(11)', nRow).html(cekprint);
              //  $('td:eq(12)', nRow).html(donlod);
              //  $('td:eq(13)', nRow).html(batal);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "spmUp.nospm", "bSortable": true,sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "sp2d.nomorSp2d", "bSortable": true,sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "spmUp.tglSpm", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "sp2d.tglSp2d", "bSortable": true,sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "sp2d.kodeCetakPotongan", "bSortable": true,sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeBeban", "bSortable": true,sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeJenis", "bSortable": true,sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "sp2d.nilaiSp2d", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "sp2d.kodeCetak", "bSortable": true,sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "namaPptk", "bSortable": true, sClass: "center", sDefaultContent: "-"},
               // {"mDataProp": "status_cetakpotongan", "bSortable": true,sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"}
             //   {"mDataProp": "id", "bSortable": false, sClass: "center"},
             //   {"mDataProp": "id", "bSortable": false, sClass: "center"},
             //   {"mDataProp": "id", "bSortable": false, sClass: "center"},
           //     {"mDataProp": "id", "bSortable": false, sClass: "center"}
            ]
        });
       // $("div.top").html("<span onclick=trigercetak()  class='Button btn dark' style='float: right'>Proses Sah</span>");
        $("div.top").html("<button id='prosespengesahan' onclick=submitvalidasi() class='btn .dark-stripe' style='float: right'>Proses Pengesahan</button>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}


function submitvalidasi( ) {
    var baseurl = getbasepath();
    var selected = [];
    var selectednoskpd = [];
    var statuscek = 0;
    $('.checkbox:checked').each(function() {
        var idspd = $(this).val();
         var nilai = $("#nilaisp2d" + idspd).val();
        
        selected.push({"idspd":idspd,"nilaisp2d":nilai });
       
        var nospd = $("#nomorsp2d" + idspd).val();
        selectednoskpd.push(nospd);
           /// return false;    
        // alert(selectednoskpd.push(nospd));
        //alert(nospd);
    });


   bootbox.confirm("Anda akan melakukan pengesahan untuk SP2D dengan nomor " + selectednoskpd.join(",") + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
              return      $.ajax({
               type: "POST",
                url: baseurl + "/sp2dsah/json/updatesp2dsah",
                contentType: "text/plain; charset=utf-8",
                dataType: "json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(selected),
                beforeSend: function() {
                    console.log(" before submit " + new Date())

                }
            }).always(function(data) {
                 
                gridsppup();
                bootbox.alert(data.responseText);
                //--print
                selectednoskpd.forEach(function(nospd) {
                    //alert(nospd);
                    printUrl(nospd);
                });

            });
        } else {
            //alert("fsdfsdf");
            bootbox.hideAll();
            return result;
        }
    });

}

function printUrl(nospd) {
    var url = getbasepath() + "/sp2dsah/printvalidasi/cetakpengesahan/" + nospd;
    printPage(url)
     
}
function closePrint() {
    document.body.removeChild(this.__container__);
}

function setPrint() {
    this.contentWindow.__container__ = this;
    this.contentWindow.onbeforeunload = closePrint;
    this.contentWindow.onafterprint = closePrint;
    this.contentWindow.print();
}

function printPage(sURL) {
    var oHiddFrame = document.createElement("iframe");
    oHiddFrame.onload = setPrint;
    oHiddFrame.style.visibility = "hidden";
    oHiddFrame.style.position = "fixed";
    oHiddFrame.style.right = "0";
    oHiddFrame.style.bottom = "0";
    oHiddFrame.src = sURL;
    document.body.appendChild(oHiddFrame);
}


function trigercetak() {
    var baseurl = getbasepath();
    var selected = [];
    var statuscek = 0;
    $('.checkbox:checked').each(function() {
        var idspd = $(this).val();
       
        var namaPptk = $("#namaPptk" + idspd).text();
         var nipPptk = $("#nipPptk" + idspd).val();
        //alert(idspd);
        //alert(namaPptk);
       
        if (namaPptk == null || namaPptk == '' || namaPptk == 'undefined' || $.trim(namaPptk).length < 1) {
            statuscek++;
        } else {
            var map = {"id": idspd,
                "namaPptk": namaPptk,
                "nipPptk": nipPptk
            }
            selected.push(map);
        }

    });
    if (statuscek == 0) {
        $.ajax({
            type: "POST",
            url: baseurl + "/sp2dsah/json/updatesp2dsah",
            contentType: "text/plain; charset=utf-8",
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(selected)
        }).always(function(data) {
            gridsppup();
            bootbox.alert(data.responseText);
        });
    } else {
        bootbox.alert("Pejabat penandatangan wajib dipilih sebelum cetak!");
    }
}


function batalspd(idspd, nosp2d) {
    var urlhapusspd = getbasepath() + "/cetaksp2d/json/hapussp2dcetak";
    bootbox.confirm("Anda akan membatalkan cetak data SP2D dengan nomor " + nosp2d + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function (result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idspd": idspd, "nospp": nosp2d})
            }).always(function (data) {
                gridsppup();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}