<%-- 
    Document   : addspmpotayat
    Created on : Nov 24, 2014, 9:51:28 AM
    Author     : Xalamaster
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPM</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">SPM Potongan Ayat</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>SPM Potogan Non Ayat - BELANJA LANGSUNG LS</div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body flip-scroll">
       <form class="form-horizontal"> 
            
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" id="tahunAnggaran" size="5"  class="m-wrap medium inputnumber" readonly="true" value="${tahunAnggaran}"  onchange="gridspmpotayat()" />
                            <input type="hidden" id="idskpd"  value="${skpd.idSkpd}" />
                        </div>
                    </div>
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" id="skpd" name="skpd"  value="${skpd.kodeSkpd}/${skpd.namaSkpd}" class="m-wrap large" size="50" readonly="true" />
                        </div>
                    </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">NO.SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input id="sspm" name="sspm" value="${nospm}" size="7" readonly="true"   class="m-wrap large"  />
                            <input id="nospm" type="hidden" name="nospm" value="${idspm}"/>
                            &nbsp; &nbsp;<!--a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/spmpotayat/spmlistpopup" title="Pilih SPM"  ><i class="icon-zoom-in"></i> Pilih</a-->
                        </div>
                    </div>
            </div> 
                        <div class="form-group">
                <label class="col-md-3 control-label">TGL.SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input id="sispm" name="sispm" value="${tglspm}" size="10" readonly="true" Class=""   />
                            <input type="hidden" id="ssspm" name="ssspm" value="${tglspm}" size="20" readonly="true" Class=""   />
                        </div>
                    </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Total SPM</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" readonly="true" id="totspm" name="totspm"  value="" class="inputmoney" onChange="" size="25" readonly="true" />
                            <input type="hidden" id="totspmhide" name="totspmhide" value="">
                        </div>
                    </div>
            </div> 
    </form>                        

    </div>
</div>
<div class="portlet box">
    <form id="formpagusppgup">
        <div class="portlet-title">

        </div>
        <div class="portlet-body">
            <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th width="85">No</th>
                        <th>Potongan</th>
                        <th width="150">Persentase</th>
                        <th width="350">Nilai</th>
                    </tr>
                </thead>
                <tbody id="btlspdtablebody" >
                    <tr>
                        <td class="center"> 1</td>
                        <td>
                            <div id="tampildataholder1" onchange="setvpot1()"></div>
                        </td>
                        <td class="center">10%</td>
                        <td>
                            <input type="text" id="vpot1" name="vpot1" class="inputmoney" readonly="true"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot1" name="cpot1"  value="">
                            <input type="hidden" id="status1" name="status1">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 2</td>
                        <td>
                            <div id="tampildataholder2" onchange="setvpot2()"><div id="tampildata2"></div></div>
                        </td>
                        <td class="center">1.5%</td>
                        <td>
                            <input type="text" id="vpot2" name="vpot2" class="inputmoney" readonly="true"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot2" name="cpot2"  value="">
                            <input type="hidden" id="status2" name="status2">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 3</td>
                        <td>
                            <div id="tampildataholder3"><div id="tampildata3"></div></div>
                        </td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot3" name="vpot3" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot3" name="cpot3"  value="">
                            <input type="hidden" id="status3" name="status3">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 4</td>
                        <td>
                            <div id="tampildataholder4"><div id="tampildata4"></div></div>
                        </td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot4" name="vpot4" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot4" name="cpot4"  value="">
                            <input type="hidden" id="status4" name="status4">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 5</td>
                        <td>
                            <div id="tampildataholder5"><div id="tampildata5"></div></div>
                        </td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot5" name="vpot5" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot5" name="cpot5"  value="">
                            <input type="hidden" id="status5" name="status5">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 6</td>
                        <td>
                            <div id="tampildataholder6"><div id="tampildata6"></div></div>
                        </td>
                        <td class="center">
                            <select id="persen6" name="persen6" onChange="hitung6()">
                                <option>Pilih</option>
                                <option value="2">2%</option>
                                <option value="4">4%</option>
                                <option value="8">8%</option>
                            </select>
                        </td>
                        <td>
                            <input type="text" id="vpot6" name="vpot6" class="inputmoney" readonly="true"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot6" name="cpot6"  value="">
                            <input type="hidden" id="status6" name="status6">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 7</td>
                        <td>
                            <div id="tampildataholder7"><div id="tampildata7"></div></div>
                        </td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot7" name="vpot7" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot7" name="cpot7"  value="">
                            <input type="hidden" id="status7" name="status7">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 8</td>
                        <td>
                            <div id="tampildataholder8"><div id="tampildata8"></div></div>
                        </td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot8" name="vpot8" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot8" name="cpot8"  value="">
                            <input type="hidden" id="status8" name="status8">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 9</td>
                        <td>
                            <div id="tampildataholder9"><div id="tampildata9"></div></div>
                        </td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot9" name="vpot9" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot9" name="cpot9"  value="">
                            <input type="hidden" id="status9" name="status9">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 10</td>
                        <td>
                            <div id="tampildataholder10"  value="">
                                <div id="tampildata10"  value=""></div>
                            </div>
                        </td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot10" name="vpot10" class="inputmoney edit2" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot10" name="cpot10">
                            <input type="hidden" id="status10" name="status10">
                        </td>
                    </tr>
                </tbody>                
            </table>
        </div>
    </form>
</div>
<div class="form-actions fluid">
     <div class="col-md-offset-3 col-md-9" align="Right">
          <button type="button" class="btn blue" onclick='ceksubmitnilai( )'>Simpan</button>
          <a  href="${pageContext.request.contextPath}/spmblls/indexspmblls" class="btn blue" onclick='ceksubmitnilai( )'>Kembali</a>
     </div>
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spmpotayat/spmpotnonayat.js"></script>  
<script type="text/javascript">
 
</script>
