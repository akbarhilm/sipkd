<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPP</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Daftar SPP UP</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah  SPP UP</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsppup"  id="refsppup"  onsubmit="return cek()" action="${pageContext.request.contextPath}/sppup/prosessimpan" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form SPP UP</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="col-md-7">
                <div class="form-group">
                    <label class="col-md-5 control-label">Tahun Anggaran:</label>
                    <div class="col-md-7">
                        <form:hidden path="id" id='id'    />
                        <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                        <input type="hidden" id="jumlahspd" name="jumlahspd" value="" />
                        <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-5 control-label">Bulan:</label>
                    <div class="col-md-7">
                        <form:input   path="bulan" id="bulan"   cssClass="required  entrybulan"  size="8"  onchange="setUraian()"  />mm/yyyy
                        <form:errors path="bulan" class="label label-important" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-5 control-label">SKPD:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)"  />
                            <form:input path="skpd.namaSkpd" id="skpd"  cssClass="required"   type="text" size="55" readonly='true' value="${skpd.kodeSkpd}/${skpd.namaSkpd}"   /> 
                            <c:if  test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                            <form:errors path="skpd.idSkpd" class="label label-important" />
                        </div>
                    </div>
                </div>           
                <div class="form-group">
                    <label class="col-md-5 control-label">No SPP:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="noSpp" id="noSpp" type="text" size="25" maxlength="20" readonly='true'  /> 
                        </div>
                    </div>
                </div>      
                <div class="form-group">
                    <label class="col-md-5 control-label">Pagu UP:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="totalAngaran" id="totalAngaran" cssClass="required" size="25"   readonly='true'  /> 
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-5 control-label">NIP Bendahara :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="nipPptk" id="nipPptk"  cssClass="required"   size="30" number='true' maxlength='18'  minlength='16'  />  
                            <form:errors path="nipPptk"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-5 control-label">Nama Bendahara :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <!-- ruleCekNamaRekanan -->
                            <form:input   path="namaPptk" id="namaPptk"  cssClass="required "   size="45" maxlength="50"   />  
                            <form:errors path="namaPptk"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-5 control-label">Kode Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="kodeBank" id='kodeBank'    />
                            <form:hidden path="idBank" id='idBank'    />
                            <form:input   path="kodeBankTransfer" id="kodeBankTransfer"  cssClass="required"   size="10" maxlength="7"  readonly="true" />  
                            <form:errors path="kodeBankTransfer"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-5 control-label">Nama Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="namaBankTransfer" id="namaBankTransfer"  cssClass="required"   size="60" maxlength="75" readonly="true"  />  
                            <form:errors path="namaBankTransfer"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-5 control-label">Nomor Rekening Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="nomorRekBank" id="nomorRekBank"  cssClass="required ruleCekCIF"   size="31" maxlength="30"  readonly="true" />  
                            <form:errors path="nomorRekBank"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-5 control-label">Uraian:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:textarea cols="70" rows="3"  path="keterangan" id="uraian"  cssClass="required" readonly="true"   />  
                            <form:errors path="keterangan"  cssClass="error"  />
                        </div>
                    </div>
                </div> 

            </div>
            <div class="col-md-4 col-md-offset-1">

                <div class="portlet box red">
                    <div class="portlet-title ">

                        <div class="caption" style="font-size: 15px">Data Bank DKI</div>       

                    </div>
                    <div class="portlet-body flip-scroll">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4 control-label">Kode Bank :</label>
                                <div class="col-md-5">
                                    <input name="dkikodebank" id="dkikodebank" type="text" readonly="true" />
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-4 control-label">Nama Bank :</label>
                                <div class="col-md-5">
                                    <input name="dkinamabank" id="dkinamabank" type="text" readonly="true" size="31" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">No Rekening :</label>
                                <div class="col-md-5">
                                    <input name="dkinorek" id="dkinorek" type="text" readonly="true" size="31" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">Nama :</label>
                                <div class="col-md-5">
                                    <TEXTAREA name="dkinama" id="dkinama" cols="30" ROWS="3" readonly="true"></TEXTAREA>
                                    <!-- <input name="dkinama" id="dkinama" type="text" readonly="true" size="31" /> -->
                                    
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>

            <div class="portlet box ">

            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onkeyup="enableddetail()" type="submit" class="btn blue" >${not empty spdBTLMaster.idSpd && spdBTLMaster.idSpd > 0? 'Simpan':'Simpan'}</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="#">Kembali</a>
                    <!--    <button type="button" class="btn blue" onclick='cek()'>CEK</button> -->
                </div>
            </div>
        </div>
    </div>
    <div class="portlet">
        <div class="portlet-title"> 
            <div class="caption"  ><i class="icon-cogs"></i>Rincian SPP UP</div>       
        </div> 
        <input type="hidden" id="banyakrinci" name="banyakrinci"  />
        <table id="spprincitable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>Nomor SPD</th>
                    <th>Tanggal SPD</th>                    
                    <th>Nilai SPD + Setoran TU</th> 
                    <th>Nilai Kontrak</th>
                    <th>Nilai Sisa SPP</th>
                    <th>Nilai SPP</th>                                           
                </tr>               
            </thead>
            <tfoot>
                <tr>
                    <th colspan="5" style="text-align:right">Total:</th>
                    <th>
                        <input type='text' id="totalspd"  name="totalspd" readonly="true" class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                    </th>
                    <th>
                        <form:input path="totalSpp" id="totalSpp" type="text"  readonly='true' cssClass="required  cekPaguUp"  style='border:none;margin:0;width:99%; text-align:right;' />
                        <!-- <input type='text' id="totalspp"   name="totalspp" readonly="true"   style='border:none;margin:0;width:99%; text-align:right;'    /> -->
                    </th>

                </tr>
            </tfoot>
            <tbody id="spprincitablebody" >
            </tbody>             
        </table> 
    </div> 

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sppup/addsppup.js"></script>  
