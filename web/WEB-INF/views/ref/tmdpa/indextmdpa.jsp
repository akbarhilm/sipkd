<%-- 
    Document   : indextmdpa
    Created on : Nov 11, 2014, 10:55:38 AM
    Author     : Xalamaster
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">TMDPA</a></li>
</ul>


<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Update TMDPA</div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="cmdTmdpa"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/tmdpa/updatetmdpa" class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <!--input type="text" name="pagupen" id="pagupen" readonly="true" size="4" value="${tahunAnggaran}"  class="m-wrap medium inputnumber"  /-->

                        <!--form:input id="tahunAnggaran" size="4"-->
                        <form:input id="tahunAnggaran" path="idDpa" size="5"  class="m-wrap medium inputnumber" readonly="true"  />
                        <!--form:hidden id="idskpd" path="idSkpd" /-->
                        <form:hidden id="idTmdpa" path="idTmdpa" />
                        <form:hidden id="skpdLevel" path="skpdLevel" onChange="skpdlevel(this.value)" />
                        <form:hidden id="idPendapatan" path="idPendapatan" onChange="idpendapatan(this.value)" />
                        <form:hidden id="idskpd" path="idSkpd"  onChange="setformben(this.value)" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <!--input type="hidden" id="idskpd" onchange="getlistspdbtl(this.value)" name="idskpd" value="${idskpd}"  /--> 
                    <!--input type="text" readonly="true"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" /-->
                    <!--form:input path="skpd" id="skpd" name="skpd"  class="m-wrap large" size="40" readonly="true" onchange="getlistspdbtl(this.value)" /-->
                    <form:input path="skpd" id="skpd" name="skpd"  class="m-wrap large" size="40" readonly="true" />
                    <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/tmdpa/skpdlistpopup?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>

                </div>
            </div>
           
            <div id="paguholder">
            <div class="form-group">
                <label class="col-md-3 control-label">PAGU Pendapatan:

                </label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="paguDpt" path="paguDpt" size="30"  class="m-wrap medium inputnumber" readonly="true"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">PAGU BTL:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="paguBtl" path="paguBtl" size="30"  class="m-wrap medium inputnumber" readonly="true"  /> 
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">PAGU BL:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="paguBl" path="paguBl" size="30"  class="m-wrap medium inputnumber" readonly="true"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">PAGU Biaya:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="paguBiaya" path="paguBiaya" size="30"  class="m-wrap medium inputnumber" readonly="true"  />
                    </div>
                </div>
            </div>
            </div>
            <div id="paholder">
            <div class="form-group">
                <label class="col-md-3 control-label">NRK / NIP PA:

                </label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="nrkPa" path="nrkPA" size="8"  class="m-wrap large" maxlength="6" minlength="6" number="true" /> <form:errors path="nrkPA" class="error" />
                        / 
                        <form:input id="nipPa" path="nipPA" size="20" class="m-wrap large" maxlength="18" minlength="18" number="true"  /> <form:errors path="nipPA" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama PA:

                </label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="namaPa" path="namaPA" size="35"  class="m-wrap large"  />
                    </div>
                </div>
            </div>
            </div>
            <br><br>
            <div id="dpaholder">
            <div class="form-group">
                <label class="col-md-3 control-label">No.DPA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="noDpa" path="noDpa" size="30"  class="m-wrap medium" readonly="true"  /> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tgl.DPA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="tglDpa" path="tglDpa" class="form-control form-control-inline input-small date-picker entrytanggal valid" size="14" readonly="true"   /> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No.DPA Perubahan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="noDpaPrbh" path="noDpaPrbh" size="30"  class="m-wrap medium" readonly="true"  /> 
                    </div>
                </div>

            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tgl.DPA Perubahan: </label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="tglDpaPrbh" path="tglDpaPrbh" class="form-control form-control-inline input-small date-picker entrytanggal valid" readonly="true" size="14"   /> 
                    </div>
                </div>
            </div>
            </div>
            <div id="bendaharaholder">
                <div id="bendaharalist">
                </div>
            </div>
            <div id="bendaharaholder">


                <div id="bendaharalistskpd3"><br><br>
                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PKBLJ: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkBenPg" path="nrkBenPg" size="8"   class="m-wrap large" maxlength="6"  minlength="6" number="true"  />
                                 / 
                                <form:input path="nipBenPg" id='nipBenPg' size='20' maxlength="18"  minlength="18" number="true"   class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PKBLJ:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaBenPg" path="namaBenPg" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PKBANTUAN: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkBenPgBantuan" path="nrkBenPgBantuan" size="8"  maxlength="6"  minlength="6" number="true"  class="m-wrap large"  />
                                 / 
                                 <form:input path="nipBenPgBantuan" id='nipBenPgBantuan' size='20'  maxlength="18"  minlength="18" number="true"  class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PKBANTUAN:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaBenPgBantuan" path="namaBenPgBantuan" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PKBTT: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkBenPgBtt" path="nrkBenPgBTT" size="8"  maxlength="6"  minlength="6" number="true"  class="m-wrap large"  />
                                 / 
                                 <form:input path="nipBenPgBTT" id='nipBenPgBtt' size='20'  maxlength="18"  minlength="18" number="true"  class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PKBTT:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaBenPgBtt" path="namaBenPgBTT" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PKBIAYA: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkBenPgPembiayaan" path="nrkBenPgPembiayaan" size="8"  maxlength="6"  class="m-wrap large"  />
                                 / 
                                 <form:input path="nipBenPgPembiayaan" id='nipBenPgPembiayaan' size='20'  maxlength="18"  minlength="18" number="true"  class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PKBIAYA:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaBenPgPembiayaan" path="namaBenPgPembiayaan" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>
                    <br>        
                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PPKBLJ: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkVerifikatorPg" path="nrkVerifikatorPg" size="8"  maxlength="6" minlength="6" number="true"   class="m-wrap large"  />
                                / 
                                <form:input path="nipVerifikatorPg" id='nipVerifikatorPg' size='20'  maxlength="18" minlength="18" number="true"   class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PPKBLJ:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaVerifikatorPg" path="namaVerifikatorPg" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>
                </div>
                

                <div id="bendaharalistcpen1">   <br><br>    
                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PKDPT: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkBenPn" path="nrkBenPn" size="8"   class="m-wrap large"  maxlength="6"  minlength="6" number="true"  />
                                / <form:input path="nipBenPn" id='nipBenPn' size='20'  maxlength="18"  class="m-wrap large" minlength="18" number="true"  />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PKDPT:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaBenPn" path="namaBenPn" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>
                    <br>        
                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PPKDPT: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkVerifikatorPn" path="nrkVerifikatorPn" size="8" maxlength="6"  minlength="6" number="true"   class="m-wrap large"  />
                                / 
                                <form:input path="nipVerifikatorPn" id='nipVerifikatorPn' size='20' maxlength="18" minlength="18" number="true"    class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PPKDPT:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaVerifikatorPn" path="namaVerifikatorPn" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>
                </div>

            </div>



            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Ubah </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/tmdpa" >Kembali</a>
                </div>
            </div>
            </script>
        </form:form>
    </div>
</div>  
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/edittmdpa.js"></script>  
<script type="text/javascript">
    $(document).ready(function() {
      $("#bendaharalistskpd3").hide();
      $("#bendaharalistcpen1").hide();
      $("#dpaholder").hide();
      $("#paholder").hide();
      $("#paguholder").show();
        //alert('sini');
        tahunanggaran();

        if ($("#idskpd").val()) {
            gettmdpa($("#idskpd").val());
        }



        $(".fancybox").fancybox({
            fitToView: true,
            autoSize: true,
            closeClick: true,
            openEffect: 'swing',
            closeEffect: 'swing',
            headers: {'X-fancyBox': true}
        });
    
    });
    function tahunanggaran() {
        $("#tahunAnggaran").val(${tahunAnggaran});

    }
/*
    function gettmdpa(idskpd) {
        $.getJSON(getbasepath() + "/tmdpa/json/gettmdpa/" + idskpd + "?tahun=${tahunAnggaran}",
                function(result) {
                    //$('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                    //$('#nilaiSpd').val(accounting.formatNumber(result.spd));

                }, "JSON");
        //alert('sukses onchange');
        //
    }
*/
    function setformben(idskpd) {
        $("#bendaharalistskpd3").hide();
        $("#bendaharalistcpen1").hide();
        $("#dpaholder").show();
        $("#paholder").show();
        $("#paguholder").show();
        console.log('sini ' + $("#idPendapatan").val() + " " + $("#skpdLevel").val());
        if ($("#idPendapatan").val() == '1') {
            console.log('masuk IF 1');
            $("#bendaharalistcpen1").show();
        }
        
        if ($("#skpdLevel").val() == '3') {
            console.log('masuk IF 3');
            $("#bendaharalistskpd3").show();
        }

    }
    function idpendapatan(objval) {
        console.log('di ID PENDAPATAN');
        if (objval == '1') {
            console.log('masuk IF 1');
            $("#bendaharalistcpen1").show();
        }
    }
    function skpdlevel(objval) {
        console.log('di SKPDLEVEL');
        if (objval == '3') {
            console.log('masuk IF 3');
            $("#bendaharalistskpd3").show();
        }
    }

</script>