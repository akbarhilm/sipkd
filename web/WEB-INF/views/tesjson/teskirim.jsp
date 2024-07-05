<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {

    });


    function kirim() {
        var varurl = getbasepath() + "/halamanpostdata/json/kirimpostdata";

        var dataac = [];

        var datajour = {
            kodebank: "111",
            norek: $('#norekkirim').val()
        };
        dataac = datajour;

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: varurl,
            data: JSON.stringify(dataac),
            success: function(data) {
                var nol = "0";
                console.log("RESPONSE : ");
                console.log(data);
                //console.log("data 37 = " + data[37]);
                //console.log("data 39 = " + data[39]);

                $('#namabank').val(data.namabank);
                $('#kodebank').val(data.kodebank);
                $('#norek').val(data.norek);
                $('#alamat').val(data.alamat);
                $('#nama').val(data.nama);
                $('#npwp').val(data.npwp);

            },
            error: function(xhr) {
                console.error(xhr);
            }
        });

    }

</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Tes Kirim JSON</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/sp2d/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Tes Kirim JSON</div>   

        </div>

        <div class="portlet-body flip-scroll">
            <div class="col-md-9">
                <div class="form-group">
                    <label class="col-md-3 control-label">Tahun Anggaran:</label>
                    <div class="col-md-4">
                        <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                    </div>
                </div>

                <div id="labelfileinbox" name="labelfileinbox" class="form-group">
                    <label class="col-md-3 control-label">Cek No Rekening (Bank DKI) :</label>
                    <div class="col-md-4">
                        <input name="norekkirim" id="norekkirim" type="text" maxlength="11"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-3 control-label">Kode Bank :</label>
                    <div class="col-md-4">
                        <input name="kodebank" id="kodebank" type="text" readonly="true" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Nama Bank :</label>
                    <div class="col-md-4">
                        <input name="namabank" id="namabank" type="text" readonly="true" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">No Rekening :</label>
                    <div class="col-md-4">
                        <input name="norek" id="norek" type="text" readonly="true" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Nama :</label>
                    <div class="col-md-4">
                        <input name="nama" id="nama" type="text" readonly="true" size="80"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Alamat :</label>
                    <div class="col-md-4">
                        <input name="alamat" id="alamat" type="text" readonly="true" size="80" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">NPWP :</label>
                    <div class="col-md-4">
                        <input name="npwp" id="npwp" type="text" readonly="true" />
                    </div>
                </div>

            </div>

            

            <div class="portlet box ">

            </div>

        </div>

    </div>         
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Left">
            <button type="button" id="btnTambah" class="btn blue" onclick='kirim()' >Kirim</button>

        </div>
    </div>                    
</form:form>


