$(document).ready(function() {
    gridterimaspm();
     
});

/*function ceknull(){
    var idskpd1 = $("#idSkpd1b").val();
    var idskpd2 = $("#idSkpd2b").val();
    if(idskpd1 === idskpd2){
        bootbox.alert("Data SKPD 2 tidak boleh sama dengan data SKPD 1");
        //document.getElementById('idSkpd2b').value='';
        var frm = document.getElementById('idSkpd2nama');
        frm.value='';
        form.idSkpd2nama.focus();
    }
    else{ //idskpd1 != idskpd2
        //bootbox.alert("sama");
    }
}*/

function gridterimaspm() {
     var baseurl = getbasepath();
    var urljson = baseurl + "/spmterima/json/getlistspmterima";
    if (typeof myTable === 'undefined') {
        myTable = $('#terimaspmtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sDom": '<"top"i>rt<"bottom"flp><"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
               // $(".checkbox").hide();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                {name: "tahun", value: $('#tahun').val()},
                //{name: "kproses", value: $('#kproses').val()},        
                 {name: "codeWilSp2dproses", value: $('#codeWilSp2dproses').val()}        
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
                var codeWilSp2dproses;
                switch (aData['codeWilSp2dproses']){
                    case "1" : codeWilSp2dproses = "Jakarta Pusat"; break;
                    case "2" : codeWilSp2dproses = "Jakarta Utara"; break;
                    case "3" : codeWilSp2dproses = "Jakarta Barat"; break;
                    case "4" : codeWilSp2dproses = "Jakarta Selatan"; break;
                    case "5" : codeWilSp2dproses = "Jakarta Timur"; break;
                    case "6" : codeWilSp2dproses = "Kepulauan Seribu"; break;
                    case "0" : codeWilSp2dproses = "Blaikota"; break;
                }
              //  var batal = "<a href='" + getbasepath() + "/spmterima/delspmterima/" + aData['idSp2d'] + "/" +aData['kodeJenis']+ "/" +aData['kodeBeban']+ "'  title='Klik disini untuk BATAL SP2D' class='icon-remove' ></a> </a>";
              //  var pilih="<a class='icon-edit' href='"+getbasepath()+"/spmterima/delspmterima/"+aData['idSpmCetak']+"'  ></a> - <a class='icon-remove' href='"+getbasepath()+"/spmterima/delspmterima/"+aData['idSpmCetak']+"' ></a>";
                var pilih="<a  href='"+getbasepath()+"/spmterima/insertspmterima/"+aData['idSpmCetak']+"'  >pilih</a>";
                
                
                $('td:eq(0)', nRow).html(index);
                //$('td:eq(1)', nRow).html(aData['skpd']['kodeSkpd'] + " / " + aData['skpd']['namaSkpd']);
                $('td:eq(1)', nRow).html(codeWilSp2dproses);
               // $('td:eq(6)', nRow).html(accounting.formatNumber(aData['nilaiSp2d'], 2, '.', ","));
                $('td:eq(10)', nRow).html(pilih);
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpmCetak", "bSortable": false,  sClass: "center"},
                {"mDataProp": "codeWilSp2dproses", "bSortable": false, sClass: "center"},
                {"mDataProp": "iSpmno", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "iSpmnoDok", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "codeJenis", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "codeBeban", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "codeSkpd", "bSortable": false, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "namaSkpd", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "dSpmCetak", "bSortable": false, sClass: "center"},
                {"mDataProp": "eSpm", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSpmCetak", "bSortable": false, sDefaultContent: "-"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

/*
function submitvalidasi( ) {
    var baseurl = getbasepath();
    var selected = [];
    var selectednoskpd = [];
    var statuscek = 0;
    var arrnospd = [];
    $('.checkbox:checked').each(function() {
        var idspd = $(this).val();
         var nilai = $("#nilaisp2d" + idspd).val();
        
        selected.push({"idspd":idspd,"nilaisp2d":nilai });
       
        var nospd = $("#nomorsp2d" + idspd).val();
        selectednoskpd.push(idspd);
        arrnospd.push(nospd);
           /// return false;    
        // alert(selectednoskpd.push(nospd));
        //alert(nospd);
    });


   bootbox.confirm("Anda akan melakukan pengesahan untuk SP2D dengan nomor " + arrnospd.join(",") + " di" + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
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
                selectednoskpd.forEach(function(idspd) {
                    //alert(nospd);
                    printUrl(idspd);
                });

            });
        } else {
            //alert("fsdfsdf");
            bootbox.hideAll();
            return result;
        }
    });

}

function printUrl(idspd) {
    var url = getbasepath() + "/sp2dsah/printvalidasi/cetakpengesahan/" + idspd;
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
*/