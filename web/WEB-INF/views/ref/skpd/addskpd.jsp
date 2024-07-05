<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listrefskpd.js"></script>

<div class="portlet box red">
    
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah Skpd</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="spdBTLMaster"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/refskpd/simpanskpd" class="form-horizontal">


            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idSkpd" id='idSkpd'   /> 
                        <form:input path="idSkpd" id='idskpd'  readonly="true"   />  
                        <form:input path="namaSkpd" id="skpd"  cssClass="required"   type="text" size="55" maxlength="80" readonly='true'  />
                        &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> 
                        <form:errors path="idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>      

            <div class="form-group">
                <label class="col-md-3 control-label">ID Induk SKPD:</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:input path="idSkpd" id="idInduk"  size="10" maxlength="50" readonly="true"   />  
                        <form:errors path="idSkpd" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kode SKPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodeSkpd" id="kodeSkpd"    size="55" maxlength="50"  />  
                        <form:errors path="kodeSkpd" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Unit Kerja:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodeUnit" id="kodeUnit"    size="55" maxlength="50"   />  
                        <form:errors path="kodeUnit" class="error" />
                    </div>
                </div>  
            </div>     
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Unit Kerja:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaUnit" id="namaUnit"    size="55" maxlength="50"   />  
                        <form:errors path="namaUnit" class="error" />
                    </div>
                </div>  
            </div>  

            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD Pendek :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaSkpdPendek" id="namaSkpdPendek"    size="55" maxlength="50"   />  
                        <form:errors path="namaSkpdPendek" class="error" />
                    </div>
                </div>  
            </div>  


            <div class="form-group">
                <label class="col-md-3 control-label">Urusan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="urusan.idUrusan" id="urusan.idUrusan"  size="5"  readonly="true"   />  
                        <span id='kodeUrusan'></span>
                        &nbsp;<a class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/program/listurusan?target='_top" title="Pilih Urusan"><i class="icon-zoom-in"></i> Pilih</a>
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Kelurahan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodeKelurahan" id="kodeKelurahan"    size="4" maxlength="1"   />  
                        <form:errors path="kodeKelurahan" class="error" />
                    </div>
                </div>  
            </div>  

            <div class="form-group">
                <label class="col-md-3 control-label">Kecamatan</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodeKecamatan" id="kodeKecamatan"    size="4" maxlength="1"   />  
                        <form:errors path="kodeKecamatan" class="error" />
                    </div>
                </div>  
            </div>  


            <div class="form-group">
                <label class="col-md-3 control-label">Aktif:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="isAktif" id="isAktif">
                            <option value="1">Aktif</option>
                            <option value="0">Tidak Aktif</option>
                        </form:select>
                        <form:errors path="isAktif" class="error" />
                    </div>
                </div>  
            </div>  

            <div class="form-group">
                <label class="col-md-3 control-label">Pendapatan</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="isPendapatan" id="isPendapatan">
                            <option value="1">Mempunyai Anggaran Pendapatan</option>
                            <option value="0">Tidak Mempunyai Anggaran Pendapatan</option>
                        </form:select>
                        <form:errors path="isPendapatan" class="error" />
                    </div>
                </div>  
            </div>  

            <div class="form-group">
                <label class="col-md-3 control-label">Level SKPD</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="levelSkpd" id="levelSkpd" >
                            <option value="1">UKPD </option>
                            <option value="2">SKPD</option>
                            <option value="3">PPKD</option>
                        </form:select>
                        <form:errors path="levelSkpd" class="error" />
                    </div>
                </div>  
            </div>  

            <div class="form-group">
                <label class="col-md-3 control-label">Neraca</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="isNeraca" id="isNeraca">
                            <option value="1">Mempunyai Neraca</option>
                            <option value="0">Tidak Mempunyai Neraca</option>
                        </form:select>
                        <form:errors path="isNeraca" class="error" />
                    </div>
                </div>  
            </div>  

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Komisi</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="kodeKomisi" id="kodeKomisi">
                            <option value="A">A </option>
                            <option value="B">B</option>
                            <option value="C">C</option>
                            <option value="D">D</option>
                        </form:select>
                        <form:errors path="kodeKomisi" class="error" />
                    </div>
                </div>  
            </div>  


            <div class="form-group">
                <label class="col-md-3 control-label">Kode Asisten</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="kodeAsisten" id="kodeAsisten">
                            <option value="1">1 </option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </form:select>
                        <form:errors path="kodeAsisten" class="error" />
                    </div>
                </div>  
            </div>  

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Berlaku</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="tahunBerlaku" id="tahunBerlaku" size="5" maxlength="4"   />  
                        <form:errors path="tahunBerlaku" class="error" />
                    </div>
                </div>  
            </div>    

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Berakhir:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="tahunBerakhir" id="tahunBerakhir" size="5" maxlength="4"   />  
                        <form:errors path="tahunBerakhir" class="error" />
                    </div>
                </div>  
            </div>    


            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" onclick="closepopup()" > Simpan </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/refskpd/index" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 


<script>
    $(document).ready(function() {
        maskkodeskpd()

    });
    function maskkodeskpd() {
        var data = $("#kodeSkpd").val();
        var arrskpd = data.split(".");
        console.log(" arrrek " + arrskpd.length)
        if (arrskpd.length == 1) {
            $("#kodeSkpd").mask(arrskpd[0] + ".9");
        }
        else if (arrskpd.length == 2) {
            $("#kodeSkpd").mask(arrskpd[0] + "." + arrskpd[1] + ".9");
        } else if (arrskpd.length == 3) {
            $("#kodeSkpd").mask(arrskpd[0] + "." + arrskpd[1] + "." + arrskpd[2] + ".9");
        } else if (arrskpd.length == 4) {
            $("#kodeSkpd").mask(arrskpd[0] + "." + arrskpd[1] + "." + arrskpd[2] + "." + arrskpd[3] + ".99");
        } else if (arrskpd.length == 5) {
            $("#kodeSkpd").mask(arrskpd[0] + "." + arrskpd[1] + "." + arrskpd[2] + "." + arrskpd[3] + "." + arrskpd[4] + ".99");
        }

    }

    function closepopup() {

        parent.$.fancybox.close();
    }
</script>